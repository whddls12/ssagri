package com.ssafy.ssagri.domain.message.controller;


import com.ssafy.ssagri.domain.message.dto.MessageRequestDto;
import com.ssafy.ssagri.domain.message.dto.MessageResponseDto;
import com.ssafy.ssagri.domain.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("message")
@Api(tags = {"[MESSAGE] 채팅 Message에 대한 API"})
@Slf4j
public class MessageController {

    private final MessageService messageService;


    @MessageMapping(value = "/chat/room/{chatRoomNo}") // /simple/chat/room/1
    @SendTo(value = "/queue/chat/room/{chatRoomNo}")
    @ApiOperation(value = "메시지 보내는 메서드")
    public ResponseEntity<MessageResponseDto> sendMessage(@RequestBody MessageRequestDto messageRequest, @DestinationVariable Long chatRoomNo) {
        log.info("messageRequest = {}", messageRequest);
        log.info("chatRoomNo = {}", chatRoomNo);
        messageRequest.setChatRoomNo(chatRoomNo);
        MessageResponseDto messageResponse = messageService.saveMessage(messageRequest);
        return ResponseEntity.ok(messageResponse);
    }

    //채팅방 번호로 메시지 조회하는 메서드
    //페이지네이션 적용
    @GetMapping("/{roomNo}")
    @Operation(summary="chatRoomNo로 메시지 조회하는 메서드")
    public ResponseEntity<Page<MessageResponseDto>> selectMessage(@PathVariable(name = "roomNo") Long roomNo, Pageable pageable){
        log.info("roomNo = {}", roomNo);
        log.info("pageable = {}", pageable);
        return ResponseEntity.ok(messageService.selectMessageResponse(roomNo, pageable));
    }


    //Test메서드 MongoDB에 INSERT 확인하기 위해 생성한 메서드
    @PostMapping("")
    @Operation(summary="chatRoomNo로 메시지 조회하는 메서드")
    public MessageResponseDto saveMessage(@RequestBody MessageRequestDto messageRequest) {
        log.info("messageRequest = {}", messageRequest);
        MessageResponseDto messageResponseDto = messageService.saveMessage(messageRequest);
        return messageResponseDto;
    }
}
