package com.ssafy.ssagri.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardWriteDto {

    Long boardNo;

    Long userNo;

    String title;

    String contents;

    Boolean allowComment;



}
