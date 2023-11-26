package com.ssafy.ssagri.entity.board;

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
@Table(name = "board_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardList extends BaseTimeEntity {

    // 글 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_list_no")
    private Long no;


    // 글을 쓰는 사람 no
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_list_writer_no",nullable = false)
    private User user;

    // 게시판 no
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no",nullable = false)
    private Board board;

    // 글 제목
    @Column(name = "board_list_title",nullable = false, length = 55)
    private String title;

//     // 댓글 작성
//    @Column(name = "board_list_show_name",nullable = false)
//    @ColumnDefault("true")
//    private boolean showName;

    // 댓글 작성
    @Column(name = "board_list_allow_comment",nullable = false)
    @ColumnDefault("true")
    private boolean allowComment;

    // 조회수
    @Column(name = "board_list_view",nullable = false)
    @ColumnDefault("0")
    private int view;

    // 글 내용
    @Lob
    @Column(name = "board_list_content",nullable = false)
    private String content;

    // 글 좋아요 수
    @Column(name = "board_list_like",nullable = false)
    @ColumnDefault("0")
    private int like;

    @Builder
    public BoardList(LocalDateTime deleteDate,LocalDateTime modifyDate,LocalDateTime createDate, Long no, User user, Board board, String title, boolean showName, boolean allowComment, int view, String content, int like) {
        this.no = no;
        this.user = user;
        this.board = board;
        this.title = title;
//        this.showName = showName;
        this.allowComment = allowComment;
        this.view = view;
        this.content = content;
        this.like = like;
        this.setCreateDate(createDate);
        this.setDeleteDate(deleteDate);
        this.setUpdateDate(modifyDate);
    }

    public void like(){
        this.like += 1;
    }

    public void click(){
        this.view+=1;
    }
}
