package com.ssafy.ssagri.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCreateDto {

    // 게시판 생성자 no
    private Long no;

    // 게시판 제목
    private String title;

//    // 게시판 설명
//    private String comment;

    // 게시판 색상
    private String color;

    // 게시판 익명 여부
    private String who;
}
