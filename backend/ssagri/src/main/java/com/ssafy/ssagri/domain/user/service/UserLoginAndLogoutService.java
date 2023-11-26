package com.ssafy.ssagri.domain.user.service;

import com.ssafy.ssagri.domain.redis.RedisService;
import com.ssafy.ssagri.domain.user.repository.UserLoginAndLogoutRepository;
import com.ssafy.ssagri.dto.user.ResponseDTO;
import com.ssafy.ssagri.dto.user.UserLoginDTO;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import static com.ssafy.ssagri.util.ResponseStatusEnum.*;
import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginAndLogoutService {

    private final UserLoginAndLogoutRepository userLoginAndLogoutRepository;
    private final RedisService redisService;

    /**
     * 로그인 : 해당 메서드를 중심으로 하여 모듈화된 메서드들이 동작
     * @param userLoginDTO
     * @return responseResult(and tokens at header)
     * @throws CustomException
     */
    @Transactional
    public ResponseEntity<ResponseDTO> loginUser(UserLoginDTO userLoginDTO, HttpServletResponse response) throws CustomException {
        //1. 유저 DB 존재 체크
        ResponseEntity<ResponseDTO> responseResult = checkAccount(userLoginDTO);
        if(userLoginAndLogoutRepository.isAccountDeleted(userLoginDTO.getEmail())) throw new CustomException(LOGIN_ACCOUT_IS_REMOVED);
        Long userNo = userLoginAndLogoutRepository.getUserNoUsingEmail(userLoginDTO.getEmail()); //userNo 찾기

        //2. 유저가 존재한다면 Access token과 Refresh token 발급, Refresh의 경우 Cookie로 설정
        String accessToken = getToken(userNo, "Access");
        String refreshToken = getToken(userNo, "Refresh");
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true); //HTTP Only Cookie set
        refreshTokenCookie.setPath("/");      //모든 경로에서 쿠키에 접근 가능
        refreshTokenCookie.setMaxAge(3600 * 12); //12시간으로 수명 설정
        response.addCookie(refreshTokenCookie);

        //3. Redis에 Refresh 토큰 저장
        saveRefreshToken(userNo, refreshToken);

        //4. 헤더에 토큰 추가하여 메시지 발급
        return addJwtTokenAtHeader(responseResult, accessToken);
    }

    //카카오용 로그인 로직
    @Transactional
    public ResponseEntity<ResponseDTO> loginUserForKakao(HttpServletResponse response, String email) throws CustomException {
        //1. 유저 DB 존재 체크 생략
        ResponseEntity<ResponseDTO> responseResult = ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LOGIN_IS_OK.getCode(),LOGIN_IS_OK.getMessage()));
        Long userNo = userLoginAndLogoutRepository.getUserNoUsingEmail(email); //userNo 찾기

        //2. 유저가 존재한다면 Access token과 Refresh token 발급, Refresh의 경우 Cookie로 설정
        String accessToken = getToken(userNo, "Access");
        String refreshToken = getToken(userNo, "Refresh");
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true); //HTTP Only Cookie set
        refreshTokenCookie.setPath("/");      //모든 경로에서 쿠키에 접근 가능
        refreshTokenCookie.setMaxAge(3600 * 12); //12시간으로 수명 설정
        response.addCookie(refreshTokenCookie);

        //3. Redis에 Refresh 토큰 저장
        saveRefreshToken(userNo, refreshToken);

        //4. 헤더에 토큰 추가하여 메시지 발급
        return addJwtTokenAtHeader(responseResult, accessToken);
    }


    private ResponseEntity<ResponseDTO> addJwtTokenAtHeader(ResponseEntity<ResponseDTO> responseResult, String accessToken) throws CustomException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Token", "Bearer " + accessToken);
//        headers.add("Refresh-Token", refreshToken); 현재는 Token이 아닌 Cookie에 저장

        // 기존 ResponseEntity 객체에 HttpHeaders 추가
        return ResponseEntity
                .status(responseResult.getStatusCode()) // 기존 상태 코드 유지
                .headers(headers) // 새로운 헤더 추가
                .body(responseResult.getBody()); // 기존 응답 본문(body) 유지;
    }

    private void saveRefreshToken(Long userNo, String refreshToken) throws CustomException{
        try {
            redisService.saveRefreshToken(userNo, refreshToken);
            log.info("[토큰 저장] {} , {}", userNo, refreshToken);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(LOGIN_SAVE_TOKEN_ERROR);
        }
    }

    public ResponseEntity<ResponseDTO> checkAccount(UserLoginDTO userLoginDTO) throws CustomException {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        if (!userLoginAndLogoutRepository.isAccountIsExists(email, password)) {
            throw new CustomException(LOGIN_HAVE_NO_ACCOUT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LOGIN_IS_OK.getCode(),LOGIN_IS_OK.getMessage()));
    }

    public String getToken(Long userNo, String tokenType) throws CustomException {
        try {
            log.info("{} {}", userNo, tokenType);
            switch (tokenType) {
                case "Access" :
                    return JwtUtil.createAccessToken(userNo);
                case "Refresh" :
                    return JwtUtil.createRefreshToken(userNo);
                default:
                    throw new Exception();
            }
        }
        catch (Exception e) {
            throw new CustomException(LOGIN_GET_TOKEN_ERROR);
        }
    }

    public ResponseEntity<?> logoutUser(HttpServletRequest request) throws CustomException {
        Long userNo;
        String accessToken;
        String rawToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(rawToken == null || !rawToken.startsWith("Bearer ")) throw new CustomException(LOGOUT_TOKEN_ERR);
        else {
            accessToken = rawToken.split(" ")[1]; //Bearer 제거
        }

        try {
            userNo = JwtUtil.getUserNo(accessToken);
        } catch (Exception e) {
            throw new CustomException(JWT_TOKEN_INVALID);
        }

        //Redis에 저장된 Refresh Token 제거
        redisService.deleteRefreshTokenByUserNo(userNo);
        log.info("[토큰 제거] {} , {}", userNo, accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LOGOUT_IS_OK.getCode(),LOGOUT_IS_OK.getMessage()));
    }
}
