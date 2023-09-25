package com.ssafy.ssagri.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardClickDto {
    Long boardNo;

    String title;

    int click;

}
