package com.ssafy.ssagri.entity.board;

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
@Table(name = "best_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BestBoard extends BaseTimeEntity {

    // 베스트 게시판
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "best_board_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_board_writer_no",nullable = false)
    private User user;

    @Column(name = "best_board_title",nullable = false, length = 55)
    private String title;

    @Column(name = "best_board_show_name",nullable = false)
    @ColumnDefault("true")
    private boolean showName;

    @Column(name = "best_board_allow_comment",nullable = false)
    private boolean allowComment;

    @Column(name = "best_board_view",nullable = false)
    @ColumnDefault("0")
    private int view;

    @Lob
    @Column(name = "best_board_content",nullable = false)
    private String content;

    @Column(name = "best_board_like",nullable = false)
    @ColumnDefault("0")
    private int like;

    @Builder
    public BestBoard(Long no, User user, String title, boolean showName, boolean allowComment, int view, String content, int like) {
        this.no = no;
        this.user = user;
        this.title = title;
        this.showName = showName;
        this.allowComment = allowComment;
        this.view = view;
        this.content = content;
        this.like = like;
    }
}
