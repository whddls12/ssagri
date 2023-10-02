package com.ssafy.ssagri.domain.chatroom.repository;

import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomListResponseDto;
import com.ssafy.ssagri.entity.chat.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomCustomRepository {
    Optional<ChatRoom> selectByUserAUserB(Long userANo, Long userBNo);

    public List<ChatRoom> selectByUserNo(Long no);


    ChatRoomListResponseDto findReceiver(Long receiverNo);

    String findNickname(Long userNo);
}
