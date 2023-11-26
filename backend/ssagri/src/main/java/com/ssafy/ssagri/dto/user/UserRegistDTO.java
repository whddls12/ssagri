package com.ssafy.ssagri.dto.user;

import com.ssafy.ssagri.entity.user.Region;
import com.ssafy.ssagri.entity.user.RegistType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private RegistType userCreateType; //회원가입 타입
    private LocalDateTime userCreateDate; //생성날짜
}
