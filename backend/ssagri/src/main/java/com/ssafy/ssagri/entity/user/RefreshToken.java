package com.ssafy.ssagri.entity.user;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * Redis Entity
 */
@RedisHash("RefreshToken")
@Setter

public class RefreshToken {
    @Id
    private Long userNo;

    private String refreshToken;

    public RefreshToken(Long userNo, String refreshToken) {
        this.userNo = userNo;
        this.refreshToken = refreshToken;
    }
}
