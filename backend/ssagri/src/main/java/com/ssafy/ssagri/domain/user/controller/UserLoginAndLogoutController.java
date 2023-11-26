package com.ssafy.ssagri.domain.user.controller;

import com.ssafy.ssagri.domain.user.service.UserLoginAndLogoutService;
import com.ssafy.ssagri.dto.user.UserLoginDTO;
import com.ssafy.ssagri.util.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 유저 로그인 및 로그아웃 컨트롤러
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "[USER]로그인, 로그아웃 컨트롤러")
public class UserLoginAndLogoutController {

    private final UserLoginAndLogoutService userLoginAndLogoutService;

    //로그인
    @Operation(summary = "로그인 기능", description = "줘야 하는 값 : UserLoginDTO, 프론트로 넘겨주는 값 : Access-Token, Refresh-Token(Cookie) \n" +
            "    LOGIN_HAVE_NO_ACCOUT(-1101, \"일치하는 계정이 없습니다.\"),\n" +
            "    LOGIN_GET_TOKEN_ERROR(-1102, \"토큰 발급 과정에서 문제가 발생했습니다.\"),\n" +
            "    LOGIN_SAVE_TOKEN_ERROR(-1103, \"토큰을 Redis로 저장하는 과정에서 문제가 발생했습니다.\"),\n" +
            "    LOGIN_IS_OK(1100, \"해당 계정이 존재합니다. (로그인 가능)\"),\n" +
            "    LOGIN_ACCOUT_IS_REMOVED(1101, \"탈퇴한 회원입니다. (로그인 불가)\"),\n" +
            "\n" +
            "네 가지 경우의 결과가 나옵니다. 200OK 받으면 성공적으로 로그아웃 된 것.\n")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        return userLoginAndLogoutService.loginUser(userLoginDTO, response);
    }

    @Operation(summary = "로그아웃 기능", description = "로그아웃 시 Header에 `Access-Token`을 주어야 합니다. HttpHeaders.AUTHORIZATION로 받을 예정입니다. \n LOGOUT_TOKEN_ERR(-1201, \"토큰 헤더가 없거나 유효하지 않습니다.\"),\n" +
            "JWT_TOKEN_INVALID(-1302, \"유효하지 않은 토큰입니다.\"),\n" +
            "LOGOUT_IS_OK(1200, \"로그아웃이 성공적으로 이루어졌습니다.\"),\n" +
            "세 가지 경우의 결과가 나옵니다. 200OK 받으면 성공적으로 로그아웃 된 것.")
    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) throws IOException {
        return userLoginAndLogoutService.logoutUser(request);
    }

}
