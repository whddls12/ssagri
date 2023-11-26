package com.ssafy.ssagri.domain.user.service;

import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.dto.user.ResponseDTO;
import com.ssafy.ssagri.util.etc.service.UtilService;
import com.ssafy.ssagri.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static com.ssafy.ssagri.util.ResponseStatusEnum.*;
import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;

@Service
@RequiredArgsConstructor
public class UserMypageService {

    private final UserRegistAndModifyRepository userRegistAndModifyRepository;
    private final UtilService utilService;

    @Transactional
    public ResponseEntity<?> changeProfile(String path, Long userNo) throws CustomException {
        try {
            userRegistAndModifyRepository.updateImage(path, userNo);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(MYPAGE_PROFILE_CHANGE_IS_OK.getCode(), MYPAGE_PROFILE_CHANGE_IS_OK.getMessage()));
        } catch (Exception e) {
            throw new CustomException(MYPAGE_PROFILE_FAIL);
        }
    }

    @Transactional
    public ResponseEntity<?> changePassword(String password, Long userNo) throws Exception {
        if(!userRegistAndModifyRepository.UserTypeIsNormal(userNo)) throw new CustomException(MYPAGE_PASSWORD_FAIL);
        userRegistAndModifyRepository.changeUserPassword(password, userNo); //변경
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(MYPAGE_PASSWORD_CHANGE_IS_OK.getCode(), MYPAGE_PASSWORD_CHANGE_IS_OK.getMessage()));
    }

    @Transactional
    public ResponseEntity<?> changeNickname(String nickname, Long userNo) {
        if (userRegistAndModifyRepository.isNicknameExists(nickname)) {
            throw new CustomException(REGISTER_NICKNAME_IS_DUPLICATE);
        }
        userRegistAndModifyRepository.changeUserNickname(nickname, userNo); //변경
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(MYPAGE_NICKNAME_CHANGE_IS_OK.getCode(), MYPAGE_NICKNAME_CHANGE_IS_OK.getMessage()));
    }

    @Transactional
    public ResponseEntity<?> deleteUser(Long userNo) {
        userRegistAndModifyRepository.deleteUser(LocalDateTime.now(), userNo); //변경
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(MYPAGE_USER_REMOVE_IS_OK.getCode(), MYPAGE_USER_REMOVE_IS_OK.getMessage()));
    }
}
