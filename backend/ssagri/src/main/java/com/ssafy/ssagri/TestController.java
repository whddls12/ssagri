package com.ssafy.ssagri;

import com.ssafy.ssagri.domain.redis.RedisService;
import com.ssafy.ssagri.domain.user.repository.RandomFirstNameRepository;
import com.ssafy.ssagri.util.mail.EmailService;

import com.ssafy.ssagri.util.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("test")
@RequiredArgsConstructor
@RestController
public class TestController {

    @Value("${kakao.restapi.key}")
    private String REST_API_KEY;

    @Value("${kakao.redirect.key}")
    private String REDIRECT_URI;

    private final RedisService redisService;
    private final OauthService oauthService;

    @GetMapping
    public String test() {
        System.out.println("TEST");
        return "TEST API 호출입니다.";
    }

    @GetMapping("oauth")
    public String test2() {
        System.out.println("TEST");
        redisService.saveKakaoAuthCode("TESTAUTH", "EMAIL");
        return "asd";
    }


}
