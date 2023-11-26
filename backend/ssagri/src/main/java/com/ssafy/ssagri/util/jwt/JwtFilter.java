package com.ssafy.ssagri.util.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ConnectException;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;


/**
 * JWT 인증 필터
 * OncePerRequestFilter Extend로 인해 모든 HTTP 요청에 대해 한 번씩만 호출된다.
 * 토큰에 대한 유효성을 검증한다.
 */
@RequiredArgsConstructor
@Component
@Slf4j
@Api(tags = "[JWT] jwt Filter 인가")
public class JwtFilter extends OncePerRequestFilter {


    //필터링 거치지 않는 API endpoint,, 테스트용
    private String[] allowedURI = new String[] {
            //Swagger
            "/api/swagger-ui/",
            "/api/swagger-resources",
            "/api/webjars/",
            "/api/v2/api-docs",

            "/api/oauth", //Oauth(kakao API)
            "/api/jwt", //jwt 요청
            "/api/user/", //로그인, 로그아웃 관련
            "/api/file/", //파일 업로드 관련
            "/api/test", //테스팅 환경
            "/api/ws" ,// 웹소켓 환경
            "/api/notification/subscribe/1",
            "/api/notification"
    };

    @Operation(summary = "필터링 거치지 않는 API endpoint 목록", description = "내부 allowedURI 저장값이 적용됩니다.")
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        for(String uri : allowedURI) {
            if(path.startsWith(uri)) {
                return true; //API 요청 주소가 사전 등록된 allowed에 있을 경우
            }
        }
        return false;
    }


    @Override
    @Operation(summary = "필터링 처리", description = "필터링 처리 과정입니다. 나올 수 있는 오류는 다음과 같습니다.  \n" +
            "{\"error\": \"Null or Not Bearer Token\"} (404) : 입력값이 Null이거나 Bearer 형태가 아닌 경우\n" +
            "\"{\\\"error\\\": \\\"Expired\\\"}\" (504) : 입력 토큰이 만료된 경우, 이 경우 토큰 갱신 로직(/jwt/fefill)을 통해 토큰 발급 후 다시 FE에서 시도해야 합니다.\n" +
            "{\"error\": \"Invalid\"} (404) : 입력 토큰이 유효하지 않은 경우, 이 경우 FE에서 추가적인 조치를 취해야 합니다.\n" +
            "이 외에 이상 없을 경우 인가 처리가 이어집니다.\n" +
            " ")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //토큰 원본 꺼내기 및 처리
        String rawToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(rawToken == null || !rawToken.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Null or Not Bearer Token\"}");
            return;
        }
        else {
            rawToken = rawToken.split(" ")[1]; //Bearer 제거
        }


        //입력받은 토큰 검증
        String tokenType = JwtUtil.isExpired(rawToken);
        log.warn("입력받은 토큰 타입 검증 : {}", tokenType);
        //토큰 타입 결과에 따른 필터 제어(Valid, Invalid, Expired)
        if(!tokenType.equals("Valid")) {
            response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
            response.setContentType("application/json");
            if(tokenType.equals("Expired")) {
                response.getWriter().write("{\"error\": \"Expired\"}");
            }
            else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid\"}");
            }
            return;
        }


        //이상 없을 경우
        filterChain.doFilter(request, response);
    }


}
