package com.ssafy.ssagri.entity.chat;

import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomResponseDto;
import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "chat_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_userA_no",nullable = false)
    private User userA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_userB_no",nullable = false)
    private User userB;

    @Column(name = "chat_room_code",nullable = false)
    private String roomCode;

    @Column(name = "chat_update_date")
    private LocalDateTime chatUpdateDate;

    @Builder
    public ChatRoom(Long no, User userA, User userB, String roomCode, LocalDateTime chatUpdateDate) {
        this.no = no;
        this.userA = userA;
        this.userB = userB;
        this.roomCode = roomCode;
        this.chatUpdateDate = chatUpdateDate;
    }

    public ChatRoomResponseDto toResponse(){
        return ChatRoomResponseDto.builder()
                .no(this.no)
                .userANo(this.userA.getNo())
                .userBNo(this.userB.getNo())
                .roomCode(this.getRoomCode())
                .updateDate(this.getUpdateDate())
                .build();
    }

    public void updateDate(){
        this.chatUpdateDate = LocalDateTime.now();
    }

}
