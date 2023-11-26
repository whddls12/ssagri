package com.ssafy.ssagri.util.jwt.service;

import com.ssafy.ssagri.domain.redis.RedisService;
import com.ssafy.ssagri.dto.user.ResponseDTO;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ssafy.ssagri.util.ResponseStatusEnum.REFILL_TOKEN_IS_OK;
import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;

@Service
@RequiredArgsConstructor
public class TokenRefillService {
    @Value("${jwt.secretkey}")
    private String secretkey;

    private final RedisService redisService;

    public void refill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rawToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(rawToken == null || !rawToken.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Null or Not Bearer Token\"}");
            return;
            
            //작업 예정
        }
    }

    public String checkCookie(HttpServletRequest request) throws CustomException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new CustomException(JWT_REFILL_COOKIE_NOT_EXIST);
    }

    public void checkRefreshTokenIsVaild(String token, Long userNo) throws CustomException {
        String parsedToken = JwtUtil.isExpired(token); //토큰 파싱
        if(!parsedToken.equals("Valid")) throw new CustomException(JWT_REFILL_COOKIE_VALUE_INVALID); //파싱값 검증
        //유효하다면 레디스에 해당 토큰 여부 확인
        if(!redisService.keyExists(userNo)) throw new CustomException(JWT_REFILL_COOKIE_REDIS_NOT_MATCHED); //레디스 내부 값 검증
    }

    public ResponseEntity<ResponseDTO> setHeaderAccessTokens(String token, Long userNo) {
        try {
            //헤더 추가
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Token", "Bearer " + JwtUtil.createRefreshToken(userNo));
            // 기존 ResponseEntity 객체에 HttpHeaders 추가
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(headers)
                    .body(new ResponseDTO(REFILL_TOKEN_IS_OK.getCode(), REFILL_TOKEN_IS_OK.getMessage()));
        } catch (Exception e) {
            throw new CustomException(JWT_REFILL_CREATE_TOKEN_ERR);
        }
    }
}
