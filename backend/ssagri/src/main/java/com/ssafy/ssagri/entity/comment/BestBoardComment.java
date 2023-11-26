package com.ssafy.ssagri.entity.comment;

import com.ssafy.ssagri.entity.board.BestBoard;
import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "best_board_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BestBoardComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "best_board_comment_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_board_no",nullable = false)
    private BestBoard bestBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_board_comment_writer_no",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_parent_comment_writer_no")
    private BestBoardComment parentComment;

    @Column(name = "best_board_comment_content",nullable = false)
    private String content;

    @Column(name = "best_board_comment_like", nullable = false)
    @ColumnDefault("0")
    private int like;

    @Builder
    public BestBoardComment(Long no, BestBoard bestBoard, User user, BestBoardComment parentComment, String content, int like) {
        this.no = no;
        this.bestBoard = bestBoard;
        this.user = user;
        this.parentComment = parentComment;
        this.content = content;
        this.like = like;
    }
}
