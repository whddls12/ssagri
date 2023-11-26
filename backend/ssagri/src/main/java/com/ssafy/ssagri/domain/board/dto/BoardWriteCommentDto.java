package com.ssafy.ssagri.domain.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardWriteCommentDto {

    private Long userNo;

    private Long boardWriteNo;

    private String writeComment;

    @Builder
    public BoardWriteCommentDto(Long userNo, Long boardWriteNo, String writeComment) {
        this.userNo = userNo;
        this.boardWriteNo = boardWriteNo;
        this.writeComment = writeComment;
    }
}


