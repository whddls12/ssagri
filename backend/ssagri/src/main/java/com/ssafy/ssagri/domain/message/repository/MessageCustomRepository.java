package com.ssafy.ssagri.domain.message.repository;

import com.ssafy.ssagri.entity.chat.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageCustomRepository {
    Page<Message> findMessagesByRoomNo(Long roomNo, Pageable pageable);

}
