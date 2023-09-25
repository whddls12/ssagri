package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRopository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByBoardClickDesc();


    List<Board> findAllByOrderByTitleAsc();
}
