package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRopository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByBoardClickDesc();


    List<Board> findAllByOrderByTitleAsc();

    Board findByNo(Long no);

    Page<Board> findAllByOrderByCreateDateAsc(Pageable pageable);

    List<Board> findTop3ByOrderByBoardLifeAsc();
}
