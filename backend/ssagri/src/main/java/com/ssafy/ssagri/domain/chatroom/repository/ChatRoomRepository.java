package com.ssafy.ssagri.domain.chatroom.repository;

import com.ssafy.ssagri.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomCustomRepository{
}
