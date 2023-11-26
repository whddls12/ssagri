package com.ssafy.ssagri.util.etc.service;

import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.UTIL_ERR;

@Service
public class UtilService {
    public Long getUserNo(HttpServletRequest request) throws Exception {
        try {
            String token = request.getHeader("Authorization").split(" ")[1];
            return JwtUtil.getUserNo(token);
        } catch (Exception e) {
            throw new CustomException(UTIL_ERR);
        }
    }

}
