package com.ssafy.ssagri.dto.user;

import com.ssafy.ssagri.entity.user.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistDTO {
    private String password;
    private String nickname;
    private String email;
    private String profile;     //이미지 URI
    private Region regions;    //지역
    private int number; //기수
}
