package com.ssafy.ssagri.entity.comment;


import com.ssafy.ssagri.entity.board.BoardList;
import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "board_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardComment extends BaseTimeEntity {

    // 댓글 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_no")
    private Long no;

    // 게시글 no
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no", nullable = false)
    private BoardList boardList;

    // 댓글 쓰는 사람 no
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_comment_writer_no",nullable = false)
    private User user;

    // 댓글 no
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_parent_comment_writer_no")
    private BoardComment parentComment;

    // 댓글 내용
    @Column(name = "board_comment_content",nullable = false)
    private String content;

    // 댓글 좋아요
    @Column(name = "board_comment_like", nullable = false)
    @ColumnDefault("0")
    private int like;

    @Builder
    public BoardComment(LocalDateTime modifyDate,LocalDateTime deleteDate,LocalDateTime createDate, Long no, BoardList boardList, User user, BoardComment parentComment, String content, int like) {
        this.no = no;
        this.boardList = boardList;
        this.user = user;
        this.parentComment = parentComment;
        this.content = content;
        this.like = like;
        this.setCreateDate(createDate);
        this.setDeleteDate(deleteDate);
        this.setUpdateDate(modifyDate);
    }
}
