package com.ssafy.ssagri.dto.user;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserPasswordChangeDTO {
    private String email;
    private String authcode;
}
