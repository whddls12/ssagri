package com.ssafy.ssagri.util;

import lombok.Getter;

/**
 *        CODE : 카테고리 (4자리 정수)
 *        MESSAFE : 메세지
 */
@Getter
public enum ResponseStatusEnum {
    //Register = 10xx
    REGIST_IS_OK(1000, "성공적으로 등록하였습니다."),
    REGIST_NICKNAME_IS_OK(1001, "닉네임이 유효합니다."),
    REGIST_EMAIL_IS_OK(1002, "이메일이 유효합니다."),
    REGIST_AUTHCODE_IS_OK(1003, "메일 인증번호가 유효합니다"),

    //LOGIN = 11xx
    LOGIN_IS_OK(1100, "해당 계정이 존재합니다. (로그인 가능)"),
    //LOGOUT = 12xx
    LOGOUT_IS_OK(1200, "로그아웃이 성공적으로 이루어졌습니다."),
    //REFILL TOKEN = 13xx
    REFILL_TOKEN_IS_OK(1300, "액세스 토큰 발급이 성공적으로 이루어졌습니다."),
    //Mail send
    MAIL_SEND_IS_OK(1400, "이메일 전송과 인증번호 등록이 성공적으로 이루어졌습니다."),
    MAIL_SEND_AND_RANDOM_CODE_IS_OK(1401, "이메일 전송과 레디스 임시 비밀번호 설정이 성공적으로 이루어졌습니다."),
    MAIL_CHECK_IS_OK(1402, "최종적으로 임시 코드로 비밀번호가 변경되었습니다."),
    //Mypage
    MYPAGE_PROFILE_CHANGE_IS_OK(1500, "프로필 이미지 변경 성공"),
    MYPAGE_PASSWORD_CHANGE_IS_OK(1501, "비밀번호 변경 성공"),
    MYPAGE_NICKNAME_CHANGE_IS_OK(1502, "닉네임 변경 성공"),
    MYPAGE_USER_REMOVE_IS_OK(1503, "회원 삭제 성공"),
        ;
    private final int code;
    private final String message;

    ResponseStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
