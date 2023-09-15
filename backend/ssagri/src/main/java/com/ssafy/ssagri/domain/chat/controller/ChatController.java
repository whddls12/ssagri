package com.ssafy.ssagri.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ChatController {

    @MessageMapping("/chat/room/1")
    @SendTo("/sub/chat/1")
    public String sendMessage(String message) {
        return message;
    }
}
