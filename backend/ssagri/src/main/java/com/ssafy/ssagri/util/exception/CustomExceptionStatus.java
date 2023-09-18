package com.ssafy.ssagri.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *        CODE : 카테고리 (4자리 정수)
 *        MESSAFE : 메세지
 */
@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {

    //Register = 1xxx
    REGISTER_NICKNAME_IS_DUPLICATE(-1000, "유저 닉네임이 중복됩니다."),
    REGISTER_EMAIL_IS_DUPLICATE(-1001, "이메일이 중복됩니다.");


    private final int code;
    private final String message;

}
