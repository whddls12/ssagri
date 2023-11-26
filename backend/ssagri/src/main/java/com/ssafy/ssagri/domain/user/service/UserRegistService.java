package com.ssafy.ssagri.domain.user.service;

import com.ssafy.ssagri.domain.redis.RedisService;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.dto.user.ResponseDTO;
import com.ssafy.ssagri.dto.user.UserPasswordChangeDTO;
import com.ssafy.ssagri.dto.user.UserRegistDTO;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import com.ssafy.ssagri.util.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.ssafy.ssagri.util.ResponseStatusEnum.*;
import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistService {

    private final UserRegistAndModifyRepository userRegistAndModifyRepository;
    private final RedisService redisService;
    private final EmailService emailService;

    @Transactional
    public ResponseEntity<ResponseDTO> registUser(UserRegistDTO userRegistDTO) throws Exception {
        userRegistAndModifyRepository.save(
                new User(userRegistDTO.getEmail(),
                        userRegistDTO.getPassword(),
                        userRegistDTO.getNickname(),
                        userRegistDTO.getProfile(),
                        userRegistDTO.getRegions(),
                        userRegistDTO.getNumber(),
                        userRegistDTO.getUserCreateType(),
                        userRegistDTO.getUserCreateDate()
                )
        );
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(REGIST_IS_OK.getCode(), REGIST_IS_OK.getMessage()));
    }

    //닉네임 중복 확인 로직
    public ResponseEntity<ResponseDTO> checkDuplicateNickname(String nickname) throws CustomException {
        if (userRegistAndModifyRepository.isNicknameExists(nickname)) {
            throw new CustomException(REGISTER_NICKNAME_IS_DUPLICATE);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(REGIST_NICKNAME_IS_OK.getCode(), REGIST_NICKNAME_IS_OK.getMessage()));
    }

    public ResponseEntity<?> checkDuplicateEmail(String email) throws CustomException {
        if (userRegistAndModifyRepository.isEmailExists(email)) {
            throw new CustomException(REGISTER_EMAIL_IS_DUPLICATE);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(REGIST_IS_OK.getCode(), REGIST_IS_OK.getMessage()));
    }

    //authCode를 통한 값을 생성하여 Redis에 보관(expire : 10분)
    public void saveAuthCodeToRedis(String authCode) throws CustomException {
        try {
            redisService.saveRegistAuthCode(authCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(MAIL_AUTH_SAVE_ERR);
        }
    }

    //메일 인증 확인용 임시 코드를 저장
    public void saveAuthCodeToRedis(String authCode, String email) throws CustomException {
        try {
            redisService.savePasswordAuthCode(authCode, email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(MAIL_AUTH_SAVE_ERR);
        }
    }

    //Authcode 유효성 판별
    public void checkAuthcode(String authcode) throws CustomException {
        if (redisService.authcodeExists(authcode)) {
            redisService.deleteRegistAuthCode(authcode); //해당 authcode 제거
            return;
        }
        throw new CustomException(REDIS_GET_VALUE_FAIL); //아닐 경우 에러 리턴
    }


    @Transactional
    public void checkEmailExist(String email) {
        if (!userRegistAndModifyRepository.isEmailExists(email))
            throw new CustomException(LOGIN_HAVE_NO_ACCOUT); //이메일 존재 확인
    }


    public String createPasswordCodeAndSaveRedis(String email) {
        String newPassword = emailService.createKey(); //랜덤 비밀번호 발급
        try { //레디스에 키값으로 저장
            redisService.savePasswordAuthCode(newPassword, email);
        } catch (Exception e) {
            throw new CustomException(LOGIN_SAVE_TOKEN_ERROR); //에러의 경우
        }
        return newPassword;
    }

    public void checkPasswordAuthIsValid(String emailKey, String authcode) {
        if (!redisService.authcodeExists(emailKey))
            throw new CustomException(REDIS_GET_VALUE_FAIL); //레디스에 email 존재하는지 확인
        if (!redisService.getAuthCode(emailKey).equals(authcode))
            throw new CustomException(REDIS_GET_MATCH_FAIL); //내부 키값과 맞나 확인
    }

    public void deleteAuthCode(String emailKey) {
        redisService.deleteRegistAuthCode(emailKey);
    }

    public void changePasswordByEmail(String email, String authcode) {
        try {
            Long userNo = userRegistAndModifyRepository.getUserNoByEmail(email); //해당 이메일을 가진 userNo 탐색
            userRegistAndModifyRepository.changeUserPassword(authcode, userNo); //userNo를 기반으로 코드 값 비밀번호 변경
        } catch (Exception e) {
            throw new CustomException(CHANGE_PASSWORD_FAIL);
        }
    }
}
