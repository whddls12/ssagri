package com.ssafy.ssagri.dto.mail;

import lombok.Data;

@Data
public class TempCodeDTO {
    private String email;
    private String code;
}
