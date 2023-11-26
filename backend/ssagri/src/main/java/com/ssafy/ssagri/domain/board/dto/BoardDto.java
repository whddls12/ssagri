package com.ssafy.ssagri.domain.board.dto;

import com.ssafy.ssagri.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Builder
public class BoardDto {

    // 게시판 no
    private Long no;

    // 게시판 생성자 no
    private String user;

    // 게시판 제목
    private String title;

    // 조회수
    private int boardClick;

    // 게시판 공개 여부
    private String showName;

    // 게시판 삭제 여부
    private boolean allowDelete;

    // 게시판 생명 주기
    private Long boardLife;

}
