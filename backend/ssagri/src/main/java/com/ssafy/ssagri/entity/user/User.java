package com.ssafy.ssagri.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long no;

    @Column(name = "user_email", unique = true, length = 40)
    private String email;


    @Column(name = "user_password", length = 20, nullable = false)
    private String password;


    @Column(name = "user_nickname", unique = true, length = 40, nullable = false)
    private String nickname;

    @Column(name = "user_temper")
    private int temper;

    @Column(name = "user_profile",length = 1000)
    private String profile;

    @Column(name = "user_point")
    private int point;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_region",nullable = false)
    private Region region;

    //기수
    @Column(name = "user_number",nullable = false)
    private int number;

    //유저 타입
    @Enumerated(EnumType.STRING)
    @Column(name = "user_create_type",nullable = false)
    private RegistType userCreateType;

    //생성 날짜
    @Column(name = "user_create_date",nullable = false)
    private LocalDateTime userCreateDate;

    //삭제 날짜
    @Column(name = "user_delete_date",nullable = true)
    private LocalDateTime userDeleteDate;

    public User(String email, String password, String nickname, String profile, Region region, int number, RegistType userCreateType, LocalDateTime userCreateDate) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profile = profile;
        this.region = region;
        this.number = number;
        this.userCreateDate = userCreateDate;
        this.userCreateType = userCreateType;
    }
}
