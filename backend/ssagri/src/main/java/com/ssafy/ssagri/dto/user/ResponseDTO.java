package com.ssafy.ssagri.dto.user;

import lombok.Data;

@Data
public class ResponseDTO {
    int code;
    String message;

    public ResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
