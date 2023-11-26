package com.ssafy.ssagri.domain.chatroom.controller;

import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomDetailResponseDto;
import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomListResponseDto;
import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomResponseDto;
import com.ssafy.ssagri.domain.chatroom.service.ChatRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
@Api(tags = {"[CHATROOM] 채팅룸에 대한 API"})
@Slf4j
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    //채팅방 생성하는 메서드(있으면 있는 것 그대로 반환)
    @PostMapping("/{userANo}/{userBNo}")
    @Operation(summary = "UserA, UserB의 No를 받아서 채팅방 생성해주는 메서드"
            ,description = "userANo, userBNo 두 개를 넘겨주면 그 둘의 채팅방을 생성하고 채팅방 정보를 넘겨준다. 이미 채팅방이 있는 경우는 그 채팅방 정보를 반환")
    public ResponseEntity<ChatRoomResponseDto> saveChatRoom(@PathVariable(name = "userANo") Long userANo, @PathVariable(name = "userBNo") Long userBNo){
        return ResponseEntity.ok(chatRoomService.saveChatRoom(userANo, userBNo));
    }

    //특정 유저가 참여하는 채팅방 전부 조회
    @GetMapping("/list/{userNo}/{sellorNo}")
    @Operation(summary = "UserA의 No를 받아서 UserA의 채팅방 목록 반환하는 메서드"
            , description = "특정 유저의 userNo를 넘겨주면 그 유저의 채팅방 전체를 List로 반환")
    public ResponseEntity<List<ChatRoomListResponseDto>> selectChatRoomByUser(@PathVariable(name = "userNo") Long userNo
        , @PathVariable(name = "sellorNo", required = false) Long sellorNo){
        List<ChatRoomListResponseDto> chatRoomResponseDtoList = chatRoomService.selectAllChatRoomByUser(userNo, sellorNo);
        return ResponseEntity.ok(chatRoomResponseDtoList);
    }

    //채팅 방 번호로 채팅방 반환하는 메서드
    @GetMapping("/{chatRoomNo}")
    @Operation(summary = "chatRoomNo를 받아서 chatRoom 반환"
            ,description = "chatRoomNo를 넘겨주면 그 채팅방 정보를 반환하는 메서드")
    public ResponseEntity<ChatRoomResponseDto> selectOneChatRoom(@PathVariable(name = "chatRoomNo") Long chatRoomNo){
        return ResponseEntity.ok(chatRoomService.selectOneChatRoom(chatRoomNo));
    }

    //채팅방 입장할 때 호출하는 메서드
    //이거 실행하면 방 없으면 방 만들고
    //이미 대화로그 있으면 대화까지 불러옴
    @GetMapping("/{userANo}/{userBNo}")
    @Operation(summary = "userANo,userBNo, size, page 넘겨줘야 함, 채팅방에 입장하는 메서드, 반환값은 채팅방 정보랑 메시지를 반환"
            ,description = "userANo, userBNo를 이용해서 채팅방 입장하는 메서드 채팅방 존재하지 않으면 생성되고, 존재하면 기존에 있던 메시지까지 반환")
    public ResponseEntity<?> selectChatRoomDetail(@PathVariable(name = "userANo") Long userANo, @PathVariable(name = "userBNo") Long userBNo, Pageable pageable){
        log.info("request Controller : {}, {}, {}", userANo, userBNo, pageable);
        ChatRoomDetailResponseDto responseDto = chatRoomService.selectChatRoomDetailByUsers(userANo, userBNo, pageable);
        log.info("response Controller Dto : {}" ,responseDto);

        return ResponseEntity.ok(responseDto);

    }

    //채팅방 삭제 메서드
    @DeleteMapping("/{userANo}/{userBNo}")
    @Operation(summary = "chatRoomNo로 채팅방 삭제하는 메서드")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable(name = "userANo") Long userANo,@PathVariable(name = "userBNo") Long userBNo){
        chatRoomService.deleteChatRoom(userANo, userBNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nickname/{userNo}")
    @Operation(summary = "chatRoom에 들어올 때 닉네임을 반환")
    public ResponseEntity<String> selectNickname(@PathVariable(name = "userNo") Long userNo){
        String nickname = chatRoomService.selectNickname(userNo);
        return ResponseEntity.ok(nickname);
    }
}