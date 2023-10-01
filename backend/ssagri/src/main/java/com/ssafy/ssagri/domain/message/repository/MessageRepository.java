package com.ssafy.ssagri.domain.message.repository;

import com.ssafy.ssagri.entity.chat.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MessageRepository extends JpaRepository<Message,String>, MessageCustomRepository {
}
