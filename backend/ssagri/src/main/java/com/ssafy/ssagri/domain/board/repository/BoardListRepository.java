package com.ssafy.ssagri.domain.board.repository;

import com.ssafy.ssagri.entity.board.BoardList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
<<<<<<< HEAD
    Page<BoardList> findAll(Pageable pageable);


}

=======

    Page<BoardList> findAll(Pageable pageable);
}
>>>>>>> 165a76e6211b191b2523a0fe73503103451099b0
