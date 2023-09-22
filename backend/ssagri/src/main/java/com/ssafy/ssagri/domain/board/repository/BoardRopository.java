package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRopository extends JpaRepository<Board, Long> {
}
