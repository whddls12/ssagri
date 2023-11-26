package com.ssafy.ssagri.domain.chatroom.dto;

import com.ssafy.ssagri.entity.user.Region;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomListResponseDto {

    // 채팅방 번호
    private Long chatRoomNo;
    // 상대방 userNo
    private Long receiverNo;
    // 상대방 닉네임
    private String receiverNickName;
    // 상대방 프로필 이미지
    private String receiverProfile;
    // 상대방 지역
    private Region receiverRegion;
    // 상대방과 마지막 대화 멘트
    private String lastMent;
    // 상대방 마지막 대화 시간
    private LocalDateTime lastDate;

    @Builder
    public ChatRoomListResponseDto(Long chatRoomNo, Long receiverNo, String receiverNickName,
        String receiverProfile, Region receiverRegion, String lastMent, LocalDateTime lastDate) {
        this.chatRoomNo = chatRoomNo;
        this.receiverNo = receiverNo;
        this.receiverNickName = receiverNickName;
        this.receiverProfile = receiverProfile;
        this.receiverRegion = receiverRegion;
        this.lastMent = lastMent;
        this.lastDate = lastDate;
    }
}
