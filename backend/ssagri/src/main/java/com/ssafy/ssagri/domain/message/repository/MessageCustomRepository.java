package com.ssafy.ssagri.domain.message.repository;

import com.ssafy.ssagri.entity.chat.Message;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageCustomRepository {
    Page<Message> findMessagesByRoomNo(Long roomNo, Pageable pageable);

    List<Message> findChatRoomByUserNo(Long userNo);
}
