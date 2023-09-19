package com.ssafy.ssagri.dto.user;

import com.ssafy.ssagri.dto.user.userenum.Regions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private String id;
    private String password;
    private String nickname;
    private String email;
    private String profile;     //이미지 URI
    private Regions regions;    //지역
    private int number; //기수
}
