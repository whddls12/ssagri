package com.ssafy.ssagri;

import com.ssafy.ssagri.domain.user.repository.UserLoginAndLogoutRepository;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "JWT 토큰 발급용 테스팅 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("jwt")
@Slf4j
public class JwtTestController {

//    @Operation(summary = "Some API", description = "설명")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "success", description = "성공"),
//            @ApiResponse(responseCode = "fail")})

    private final UserLoginAndLogoutRepository userLoginAndLogoutRepository;

    @Value("${jwt.secretkey}")
    String key;

    @Operation(summary = "Acess-Token을 발급합니다.", description = "해당 api로 요청을 보낼 경우 Access Token을 ResponseEntity<String> 형태로 발급합니다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리됨", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "요청한 자원을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 오류 발생")
    })
    @GetMapping("at")
    public String createAccessToken() {
        log.warn("ac 생성 : {}");
        return JwtUtil.createAccessToken(1L);
    }

    @GetMapping("rt")
    public void refreshTest() {
        JwtUtil.createRefreshToken(1L);
    }

    @GetMapping("test")
    public void testBoard() {
        System.out.println("test");
    }
}
