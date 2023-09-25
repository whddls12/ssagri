package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.BoardList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
    Page<BoardList> findAll(Pageable pageable);
}
