package com.ssafy.ssagri.domain.notification;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
@CrossOrigin("*")
@Api(tags = {"[NOTIFICATION] 알림을 위한 등록 API"})
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/subscribe/{userNo}")
    @Operation(summary = "알림을 구독하는 메서드"
            ,description = "userNo를 넘겨줘야 한다,알림을 구독하는 메서드 로그인 하면 이 메서드를 호출해서 본인에게 오는 알람을 수신해야 함")
    public SseEmitter subscribe(@PathVariable(name = "userNo") Long userNo) {
        SseEmitter sseEmitter = notificationService.addSseEmitter(userNo);
        return sseEmitter;
    }

    @GetMapping("")
    @Operation(summary = "Test Method")
    public void test() {
        log.info("test");
        notificationService.sendMessageTest();
    }
}
