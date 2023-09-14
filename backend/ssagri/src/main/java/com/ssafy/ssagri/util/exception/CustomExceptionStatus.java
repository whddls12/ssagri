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


    USER_NOT_ENOUGH_COIN(-5000, "현재 가지고 있는 골드가 부족합니다.");

    private final int code;
    private final String message;

}
