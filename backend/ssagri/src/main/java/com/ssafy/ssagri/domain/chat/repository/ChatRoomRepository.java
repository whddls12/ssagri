package com.ssafy.ssagri.domain.chat.repository;

import com.ssafy.ssagri.entity.chat.ChatRoom;
import com.ssafy.ssagri.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,ChatRoomCustomRepository {
}
