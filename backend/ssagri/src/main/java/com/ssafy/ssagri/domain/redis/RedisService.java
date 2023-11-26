package com.ssafy.ssagri.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * RedisRepository가 아닌 RedisTemplate를 통한 직접 조정
 * 로그인 시 접속 정보 저장, 인증번호 저장 등
 * 인메모리 휘발성 정보등을 저장 및 관리
 * 추후 모듈화 필요
 */
@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    public void saveRefreshToken(Long userNo, String tokenValue) {
        String key = "RT" + userNo;
        stringRedisTemplate.opsForValue().set(key, tokenValue);
        stringRedisTemplate.expire(key, 3600 * 12, TimeUnit.SECONDS);
    }

    public void deleteRefreshTokenByUserNo(Long userNo) {
        String key = "RT" + userNo;
        stringRedisTemplate.delete(key);
    }

    public Boolean keyExists(Long userNo) {
        String key = "RT" + userNo;
        return stringRedisTemplate.hasKey(key);
    }

    public void saveRegistAuthCode(String authcode) {
        stringRedisTemplate.opsForValue().set(authcode, "authcode");
        stringRedisTemplate.expire(authcode, 600, TimeUnit.SECONDS);
    }

    public void deleteRegistAuthCode(String authcode){
        stringRedisTemplate.delete(authcode);
    }

    public void savePasswordAuthCode(String authcode, String email) {
        String key = "[PW-CHECK-CODE]"+email;
        stringRedisTemplate.opsForValue().set(key, authcode);
        stringRedisTemplate.expire(key, 300, TimeUnit.SECONDS);
    }

    //동일 로직 모듈화 필요
    public void saveKakaoAuthCode(String authcode, String email) {
        String key = "[KAKAO-CHECK-CODE]"+email;
        stringRedisTemplate.opsForValue().set(key, authcode);
        stringRedisTemplate.expire(key, 300, TimeUnit.SECONDS);
    }


    public Boolean authcodeExists(String authcode) {
        return stringRedisTemplate.hasKey(authcode);
    }

    public String getAuthCode(String value) {
        return stringRedisTemplate.opsForValue().get(value);
    }
}
