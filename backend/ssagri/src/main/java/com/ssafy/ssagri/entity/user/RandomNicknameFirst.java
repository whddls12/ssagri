package com.ssafy.ssagri.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "random_nickname_first")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomNicknameFirst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nickname_no")
    private Long no;

    private String firstName;

}
