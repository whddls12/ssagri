package com.ssafy.ssagri.domain.message.repository;

import com.ssafy.ssagri.entity.chat.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message,String> {

}
