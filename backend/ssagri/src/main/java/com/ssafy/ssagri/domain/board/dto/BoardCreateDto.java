package com.ssafy.ssagri.domain.board.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class BoardCreateDto {

    // 게시판 생성자 no
     Long userNo;

    // 게시판 제목
     String title;

//    // 게시판 설명
     String comment;

    // 게시판 색상
//    private String color;

    // 게시판 익명 여부
     String who;

    @Builder
    public BoardCreateDto(Long userNo, String title, String comment, String who) {
        this.userNo = userNo;
        this.title = title;
        this.comment = comment;
        this.who = who;
    }
}
