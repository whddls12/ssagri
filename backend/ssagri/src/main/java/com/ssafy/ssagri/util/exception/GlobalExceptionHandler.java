package com.ssafy.ssagri.util.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;

/**
 * 전역적인 AOP 관련 로직을 수행하는 핸들러입니다.
 */
@ControllerAdvice //전역 예외 처리
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ExpiredJwtException.class)
    public void handleExpiredJwtException(ExpiredJwtException ex) {
        log.error("[AOP] handleExpiredJwtException Err"); // 다음 예외 발생 시 로깅을 하지 않음
    }

    @ExceptionHandler(SignatureException.class)
    public void handleSignatureException(SignatureException ex) {
        log.error("[AOP] handleSignatureException Err"); // 다음 예외 발생 시 로깅을 하지 않음
    }

    //CustomErr가 발생했을 때, 에러 코드를 반환하도록 전역 처리
    @ExceptionHandler({CustomException.class, Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        HttpStatus status;
        CustomResponse response = new CustomResponse();

        if (ex instanceof CustomException) {
            status = HttpStatus.BAD_REQUEST;
            response.setCode(((CustomException) ex).getCustomExceptionStatus().getCode());
            response.setMessage(((CustomException) ex).getCustomExceptionStatus().getMessage());
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setCode(-500);
            response.setMessage("알 수 없는 에러가 발생하였습니다. 에러 원문 : " + ex.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class CustomResponse {
    int code;
    String message;
}
