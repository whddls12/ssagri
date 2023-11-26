package com.ssafy.ssagri.util.oauth.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssagri.domain.redis.RedisService;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import com.ssafy.ssagri.util.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    @Value("${kakao.restapi.key}")
    private String REST_API_KEY;

    @Value("${kakao.redirect.key}")
    private String REDIRECT_URI;

    @Value("${kakao.client.secret}")
    private String CLIENT_SECRET;

    private final EmailService emailService;
    private final RedisService redisService;
    private final UserRegistAndModifyRepository userRegistAndModifyRepository;

    public String getToken(String code) {
        //Kakao 보낼 API
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //요청 보내기
        Map<String, Object> tokenResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", REST_API_KEY)
                        .queryParam("redirect_uri", REDIRECT_URI)
                        .queryParam("code", code)
                        .queryParam("client_secret", CLIENT_SECRET)
                        .build())
                .retrieve().bodyToMono(Map.class).block();

        log.info("[KAKAO]액세스 토큰 발급 완료!");
        return (String) tokenResponse.get("access_token");
    }

    public String[] getUserInfo(String accessToken) {
        // webClient 설정
        WebClient kakaoApiWebClient =
                WebClient.builder()
                        .baseUrl("https://kapi.kakao.com")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();

        // info api 설정
        Map<String, Object> infoResponse =
                kakaoApiWebClient
                        .post()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v2/user/me")
                                .build())
                        .header("Authorization", "Bearer " + accessToken)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();


        log.info("[OauthService]토큰으로 받아낸 유저 정보 : {}" + infoResponse);
        //Map 내부 값 파싱
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(infoResponse);
            JsonNode jsonNode = objectMapper.readTree(json);

            String nickname = jsonNode.path("properties").path("nickname").asText();
            String profileImageUrl = jsonNode.path("properties").path("profile_image").asText();
            String email = jsonNode.path("kakao_account").path("email").asText();

            //랜덤 코드 만들어 Redis에 저장
            String authcode = emailService.createKey();
            redisService.saveKakaoAuthCode(authcode, email);
            log.info("[OauthService]카카오 인가 코드 생성 : {} {}", authcode, email);

            return new String[]{nickname, profileImageUrl, email, authcode};

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(OAUTH_USERINFO_PARSING_ERR);
        }
    }

    public String getKakaoLoginPage() {
        return "https://kauth.kakao.com/oauth/authorize?client_id=" + REST_API_KEY + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }


    public void checkAndDeleteRedisCode(String email, String authcode) {
        if(!userRegistAndModifyRepository.isEmailExists(email)) throw new CustomException(OAUTH_KAKAO_NOT_VALID_EMAIL);
        if(!redisService.authcodeExists("[KAKAO-CHECK-CODE]"+email)) throw new CustomException(OAUTH_KAKAO_NOT_VALID_EMAIL); //레디스에 값 존재하는지 체크
        if(!redisService.getAuthCode("[KAKAO-CHECK-CODE]"+email).equals(authcode)) throw new CustomException(OAUTH_KAKAO_NOT_VALID_AUTHCODE); //값의 value가 코드와 맞는지 체크
        redisService.deleteRegistAuthCode("[KAKAO-CHECK-CODE]"+email); //해당 값 삭제 과정 진행
    }
}
