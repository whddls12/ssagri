package com.ssafy.ssagri.domain.board.dto;


import com.ssafy.ssagri.entity.board.Board;
import com.ssafy.ssagri.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Builder
public class BoardListDto {

    // 글 no
    private Long no;

    // 글을 쓰는 사람 no
    private String user;

    // 게시판 이름
    private String boardName;

    // 게시판 no
    private Long boardNo;

    // 게시판 조회수
    private int boardView;

    // 게시판 생명 주기
    private Long boardLife;

    // 글 제목
    private String title;

    // 댓글 허가 여부
    private boolean allowComment;

    // 조회수
    private int view;

    // 글 내용
    private String content;

    // 글 좋아요 수
    private int like;

    // 글 작성 시간이 얼마나 지났는 지
    private String writeTime;

    // 댓글 갯수
    private int commentCount;


}
