package com.ssafy.ssagri;

import com.ssafy.ssagri.entity.chat.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
