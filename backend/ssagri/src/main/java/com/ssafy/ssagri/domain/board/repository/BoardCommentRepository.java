package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.BoardList;
import com.ssafy.ssagri.entity.comment.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    List<BoardComment> findAllByBoardList(BoardList BoardList);

}
