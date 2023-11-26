package com.ssafy.ssagri.domain.message.dto;


import com.ssafy.ssagri.entity.chat.Message;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MessageRequestDto {
    private Long chatRoomNo;
    private Long senderNo;
    private Long receiverNo;
    private String content;

    @Builder
    public MessageRequestDto(Long chatRoomNo, Long senderNo, Long receiverNo, String content) {
        this.chatRoomNo = chatRoomNo;
        this.senderNo = senderNo;
        this.receiverNo = receiverNo;
        this.content = content;
    }

    public Message toEntity(String senderNickName, String receiverNickName){
        Message message = Message.builder()
                .roomNo(chatRoomNo)
                .senderNo(senderNo)
                .senderNickName(senderNickName)
                .receiverNo(receiverNo)
                .receiverNickName(receiverNickName)
                .content(content)
                .time(LocalDateTime.now())
                .build();
        return message;
    }
}
