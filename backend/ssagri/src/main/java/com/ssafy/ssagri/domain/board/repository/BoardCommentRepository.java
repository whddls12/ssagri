package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.comment.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
