package com.ssafy.ssagri.entity.chat;

import com.ssafy.ssagri.domain.message.dto.MessageResponseDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

//@Document(collection = "Message")
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_no")
    private Long roomNo;
    @Column(name = "sender_no")
    private Long senderNo;
    @Column(name = "sender_nick_name")
    private String senderNickName;
    @Column(name = "receiver_no")
    private Long receiverNo;
    @Column(name = "receiver_nick_name")
    private String receiverNickName;
    private String content;
    private LocalDateTime time;

    @Builder
    public Message(Long id, Long roomNo, Long senderNo, String senderNickName, Long receiverNo, String receiverNickName, String content, LocalDateTime time) {
        this.id = id;
        this.roomNo = roomNo;
        this.senderNo = senderNo;
        this.senderNickName = senderNickName;
        this.receiverNo = receiverNo;
        this.receiverNickName = receiverNickName;
        this.content = content;
        this.time = time;
    }

    public MessageResponseDto toResponse(){
        MessageResponseDto messageResponse = MessageResponseDto.builder()
                .chatRoomNo(roomNo)
                .senderNo(senderNo)
                .senderNickName(senderNickName)
                .receiverNo(receiverNo)
                .receiverNickName(receiverNickName)
                .content(content)
                .time(time)
                .build();
        return messageResponse;
    }
}
