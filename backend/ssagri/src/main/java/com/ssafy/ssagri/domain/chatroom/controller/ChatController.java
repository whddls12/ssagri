package com.ssafy.ssagri.domain.chatroom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/room/1")
    @SendTo("/sub/chat/room/1")
    public String sendMessage(String message) {

        System.out.println("message = " + message);
        return message;
    }
}
