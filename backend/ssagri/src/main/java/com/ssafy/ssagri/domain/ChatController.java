package com.ssafy.ssagri.domain;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/send")
    @SendTo("/sub/messages")
    public String sendMessage(String message) {
        return message;
    }
}
