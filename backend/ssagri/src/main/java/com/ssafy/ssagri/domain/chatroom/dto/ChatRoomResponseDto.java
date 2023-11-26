package com.ssafy.ssagri.domain.chatroom.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomResponseDto {
    private Long no;
    private Long userANo;
    private Long userBNo;
    private String roomCode;
    private LocalDateTime updateDate;

    @Builder
    public ChatRoomResponseDto(Long no, Long userANo, Long userBNo, String roomCode, LocalDateTime updateDate) {
        this.no = no;
        this.userANo = userANo;
        this.userBNo = userBNo;
        this.roomCode = roomCode;
        this.updateDate = updateDate;
    }
}
