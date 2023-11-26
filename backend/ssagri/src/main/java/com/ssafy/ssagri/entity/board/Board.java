package com.ssafy.ssagri.entity.board;

import com.ssafy.ssagri.entity.auction.AuctionProductImage;
import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    // 게시판 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no",nullable = false)
    private Long no;

    // 게시판 생성자 no
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_create_user_no",nullable = false)
    private User user;

    // 게시판 제목
    @Column(name = "board_title", nullable = false)
    private String title;

    // 조회수
    @Column(name = "board_click", nullable = true)
    private int boardClick;

    // 게시판 색상
    @Column(name = "board_color", nullable = true)
    private String boardColor;

    // 게시판 생성 시간
//    @Column(name = "board_create_time", nullable = true)
//    private LocalDateTime createTime;

    // 게시판 익명 여부
    @Column(name = "board_show_name",nullable = false)
    private String showName;

    // 게시판 삭제 여부
    @Column(name = "board_allow_delete",nullable = false)
    @ColumnDefault("1")
    private boolean allowDelete;

    // 생명주기
    @Column(name = "board_life",nullable = false)
    private LocalDateTime boardLife;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    List<BoardList> boardWrites = new ArrayList<>();


    @Builder
    public Board(User user, String title, String showName, LocalDateTime boardLife, int boardClick, LocalDateTime deleteTime
    , LocalDateTime createTime) {
        this.user = user;
        this.title = title;
        this.showName = showName;
        this.boardClick = boardClick;
        this.boardLife = boardLife;
        this.setDeleteDate(deleteTime);
        this.setCreateDate(createTime);
    }

    public void click(){
        this.boardClick+=1;
    }

    public void delete(){
        this.setDeleteDate(LocalDateTime.now());
    }
}
