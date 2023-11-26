package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.Board;
import com.ssafy.ssagri.entity.board.BoardList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
    Page<BoardList> findAllByBoardOrderByCreateDateAsc(Board board,Pageable pageable);



    BoardList findByNo(Long no);




}
