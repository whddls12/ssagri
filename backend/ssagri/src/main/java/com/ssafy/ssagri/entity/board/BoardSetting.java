package com.ssafy.ssagri.entity.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "board_setting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_setting_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_list_no")
    private Board board;


}
