package com.ssafy.ssagri.domain.message.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto {
    private Long chatRoomNo;
    private Long senderNo;
    private String senderNickName;
    private Long receiverNo;
    private String receiverNickName;
    private String content;
    private LocalDateTime time;

    @Builder
    public MessageResponseDto(Long chatRoomNo, Long senderNo, String senderNickName, Long receiverNo, String receiverNickName, String content, LocalDateTime time) {
        this.chatRoomNo = chatRoomNo;
        this.senderNo = senderNo;
        this.senderNickName = senderNickName;
        this.receiverNo = receiverNo;
        this.receiverNickName = receiverNickName;
        this.content = content;
        this.time = time;
    }
}
