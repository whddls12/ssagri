package com.ssafy.ssagri.domain.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardWriteDto {

    Long boardNo;

    Long userNo;

    String title;

    String contents;

    Boolean allowComment;


    @Builder
    public BoardWriteDto(Long boardNo, Long userNo, String title, String contents, Boolean allowComment) {
        this.boardNo = boardNo;
        this.userNo = userNo;
        this.title = title;
        this.contents = contents;
        this.allowComment = allowComment;
    }
}
