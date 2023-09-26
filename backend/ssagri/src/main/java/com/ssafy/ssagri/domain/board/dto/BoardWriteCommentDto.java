package com.ssafy.ssagri.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardWriteCommentDto {

    private Long userNo;

    private Long boardWriteNo;

    private String writeComment;

}
