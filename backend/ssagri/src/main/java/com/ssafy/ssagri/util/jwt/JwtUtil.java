package com.ssafy.ssagri.util.jwt;

import com.ssafy.ssagri.util.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.JWT_PARSING_ERR;

/**
 * Header에 담긴 원본 토큰 값 파싱
 * 토큰의 생성, 유효성 검증, 내부 값 추출
 */
@Slf4j
@Api(tags = "JWT 관련 유틸 기능들")
public class JwtUtil {
    //Key : 추후 보안상 변경 조치 필요
    private final static String secretKey = "ssafy9kiB209teamSsaguriFightingssafy9kiB209teamSsaguriFightingssafy9kiB209teamSsaguriFighting";
    private static long hour = 1000 * 60 * 60L;
    private static long accessExpireTime = hour / 2; //액세스 토큰 만료시간 30분 -> 현재 테스트용으로 기한 연장
    private static long refreshExpireTime = hour * 12; //리프레시 토큰 만료시간 12시간

    @Operation(summary = "액세스 토큰 생성", description = "userNo 입력, payload에 추가하여 생성")
    public static String createAccessToken(Long userNo) {
        Claims claims = Jwts.claims();
        claims.put("userNo", userNo); //payload
        claims.put("tokenType", "Access");
        String accessToken = tokenBuilder(claims, accessExpireTime);

        log.info("[TOKEN]AT 확인 {}",accessToken);
        return accessToken;
    }

    @Operation(summary = "리프레쉬 토큰 생성", description = "userNo 입력, payload에 추가하여 생성")
    public static String createRefreshToken(Long userNo) {
        Claims claims = Jwts.claims();
        claims.put("userNo", userNo); //payload
        claims.put("tokenType", "Refresh");
        String refreshToken = tokenBuilder(claims, refreshExpireTime);

        log.info("[TOKEN]RT 확인 {}",refreshToken);
        return refreshToken;
    }

    //토큰 만드는 로직
    public static String tokenBuilder(Claims claims, Long expireTime) {
        return Jwts.builder() // 리프레쉬 토큰을 생성
                .setClaims(claims) // claim은 비어있음
                .setIssuedAt(new Date(System.currentTimeMillis())) // 현재 시간
                .setExpiration(new Date(System.currentTimeMillis() + expireTime)) // 언제까지
                .signWith(SignatureAlgorithm.HS256, secretKey) // 어떤 키로 사인할지
                .compact();
    }

    @Operation(summary = "토큰 유효성 판별", description = "토큰 파싱하여 종류에 따른 값 출력 : Valid, Expired, Invalid")
    public static String isExpired(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return "Valid";
        } catch (ExpiredJwtException e) {
            return "Expired"; //만료된 토큰
        } catch (Exception e) {
            return "Invalid"; //잘못된 토큰
        }
    }

    @Operation(summary = "payload 파싱", description = "액세스 토큰 받아 userNo 파싱하기")
    public static Long getUserNo(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userNo", Long.class);
    }

    @Operation(summary = "토큰 타입 꺼내기", description = "토큰 받아 ac/rc 판별")
    public static String getTokenType(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody().get("tokenType", String.class);
        } catch (ExpiredJwtException e) {
            return "ERROR";
        }
    }


}
