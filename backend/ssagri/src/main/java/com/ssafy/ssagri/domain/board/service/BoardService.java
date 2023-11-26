package com.ssafy.ssagri.domain.board.service;

import com.ssafy.ssagri.domain.board.dto.*;
import com.ssafy.ssagri.domain.board.repository.BoardCommentRepository;
import com.ssafy.ssagri.domain.board.repository.BoardListRepository;
import com.ssafy.ssagri.domain.board.repository.BoardRopository;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.entity.board.Board;
import com.ssafy.ssagri.entity.board.BoardList;
import com.ssafy.ssagri.entity.comment.BoardComment;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    final private BoardRopository boardRopository;
    final private UserRegistAndModifyRepository userRegistAndModifyRepository;
    final private BoardListRepository boardListRepository;
    final private BoardCommentRepository boardCommentRepository;

    // 조회수로 오름차순한 게시판이름이랑 조회수 출력
    public List<BoardClickDto> boardClickList(){

        List<Board> boardlist = boardRopository.findAllByOrderByBoardClickDesc();

        List<BoardClickDto> result = new ArrayList<>();

        for(int i=0;i<boardlist.size();i++){
            BoardClickDto boardClickDto = BoardClickDto.builder()
                    .boardNo(boardlist.get(i).getNo())
                    .title(boardlist.get(i).getTitle())
                    .click(boardlist.get(i).getBoardClick()).build();

            result.add(boardClickDto);
        }
        return result;
    }

    // 게시판 title로 오름차순 리스트 출력
    public List<BoardClickDto> boardTitleList(){

        List<Board> boardlist = boardRopository.findAllByOrderByTitleAsc();

        List<BoardClickDto> result = new ArrayList<>();

        for(int i=0;i<boardlist.size();i++){
            BoardClickDto boardClickDto = BoardClickDto.builder()
                    .boardNo(boardlist.get(i).getNo())
                    .title(boardlist.get(i).getTitle())
                    .click(boardlist.get(i).getBoardClick()).build();

            result.add(boardClickDto);
        }
        return result;
    }

    // 게시판 모두 출력
    public Page<BoardDto> boardList(Pageable pageable){
        Page<Board> allboardlist = boardRopository.findAllByOrderByCreateDateAsc(pageable);

        List<Board> boardlist = allboardlist.getContent();

        List<BoardDto> result = new ArrayList<>();

        for(int i=0;i<boardlist.size();i++){
            BoardDto boardDto = BoardDto.builder()
                    .no(boardlist.get(i).getNo())
                    .user(boardlist.get(i).getUser().getNickname())
                    .title(boardlist.get(i).getTitle())
                    .boardClick(boardlist.get(i).getBoardClick())
                    .showName(boardlist.get(i).getShowName())
                    .boardLife(ChronoUnit.DAYS.between(LocalDateTime.now(),boardlist.get(i).getBoardLife()))
                    .allowDelete(boardlist.get(i).isAllowDelete()).build();

            result.add(boardDto);
        }

        return new PageImpl<>(result, allboardlist.getPageable(), allboardlist.getTotalElements());
    }

    // 게시판 등록
    @Transactional
    public void boardregist(BoardCreateDto boardCreateDto){
        log.info("boardCreateDto = {}", boardCreateDto);


        Optional<User> findUser = userRegistAndModifyRepository.findById(boardCreateDto.getUserNo());
        log.info("user");
        if (findUser.isEmpty()){
            log.info("empty");
            throw new CustomException(CustomExceptionStatus.USER_DOES_NOT_EXSIST);
        }else{
            log.info("present");
        }


        Board board = Board.builder()
                .user(findUser.get())
                .title(boardCreateDto.getTitle())
//                .boardColor(boardCreateDto.getColor())
                .boardClick(0)
//                .createTime(LocalDateTime.now())
                .showName(boardCreateDto.getWho())
                .boardLife(LocalDateTime.now().plusDays(7)).build();


        boardRopository.save(board);

    }

    // 게시판 클릭 시 조회수 증가
    @Transactional
    public void boardClick(Long boardNo){

        Board board1 = boardRopository.findByNo(boardNo);


        board1.click();


    }

    // 게시글 클릭 시 조회수 증가
    @Transactional
    public void boardListClick(Long boardListNo){

        BoardList board = boardListRepository.findByNo(boardListNo);


        board.click();


    }

    // 게시글 등록
    @Transactional
    public void boardWrite(BoardWriteDto boardWriteDto){

        Optional<User> findUser = userRegistAndModifyRepository.findById(boardWriteDto.getUserNo());
        if (findUser.isEmpty()){
            System.out.println("empty");
        }else{
            System.out.println("present");
        }


        BoardList boardList = BoardList.builder()
                .user(findUser.get())
                .board(boardRopository.findByNo(boardWriteDto.getBoardNo()))
                .title(boardWriteDto.getTitle())
                .allowComment(boardWriteDto.getAllowComment())
                .view(0)
                .content(boardWriteDto.getContents())
                .like(0).build();

        Board board = boardRopository.findByNo(boardWriteDto.getBoardNo());



       board.builder()
               .boardLife(boardRopository.findByNo(boardWriteDto.getBoardNo())
                       .getBoardLife().plusHours(1)).build();

        boardRopository.save(board);

        boardListRepository.save(boardList);

    }

    // 게시글에 좋아요 누르기
    @Transactional
    public void writeLike(Long writeNo){
        BoardList boardList1 = boardListRepository.findByNo(writeNo);

       boardList1.like();

    }

    // 게시글 바 출력
    public BoardDto boardWriteBar(Long boardNo)
    {

        Board board = boardRopository.findByNo(boardNo);

        BoardDto boardDto = BoardDto.builder()
                .boardLife(ChronoUnit.DAYS.between(LocalDateTime.now(),board.getCreateDate()))
                .boardClick(board.getBoardClick())
                .title(board.getTitle()).build();

        return boardDto;
    }

    // 게시글 모두 출력
    public Page<BoardListDto> boardWriteList(Long boardNo, Pageable pageable){

        Board board = boardRopository.findByNo(boardNo);

        Page<BoardList> allBoardWriteList = boardListRepository.findAllByBoardOrderByCreateDateAsc(board,pageable);

        List<BoardList> boardWriteList = allBoardWriteList.getContent();

        List<BoardListDto> result1 = new ArrayList<>();


        for(int i=0;i<boardWriteList.size();i++){

            System.out.println(boardWriteList.get(i).getNo());

            String time;

            if(ChronoUnit.SECONDS.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) > 60){

                time =  ChronoUnit.MINUTES.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) + "분 전";
            }
            else if(ChronoUnit.SECONDS.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) > 3600){

                time =  ChronoUnit.HOURS.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) + "시간 전";
            }
            else if(ChronoUnit.SECONDS.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) > 86400){
                time = ChronoUnit.DAYS.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) + "일 전";
            }
            else {
                time = ChronoUnit.SECONDS.between(boardWriteList.get(i).getCreateDate(),LocalDateTime.now()) + "초 전";
            }


//            String boardLife = boardWriteList.get(i).getBoard().getBoardLife().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            BoardListDto boardListDto = BoardListDto.builder()
                    .no(boardWriteList.get(i).getNo())
                    .user(boardWriteList.get(i).getUser().getNickname())
//                    .boardName(boardWriteList.get(i).getBoard().getTitle())
//                    .boardView(boardWriteList.get(i).getBoard().getBoardClick())
//                    .boardLife(ChronoUnit.DAYS.between(LocalDateTime.now(),boardWriteList.get(i).getCreateDate()))
                    .title(boardWriteList.get(i).getTitle())
                    .view(boardWriteList.get(i).getView())
                    .like(boardWriteList.get(i).getLike())
                    .writeTime(time)
                    .allowComment(boardWriteList.get(i).isAllowComment())
                    .commentCount(boardCommentRepository.findAllByBoardList(boardWriteList.get(i)).size())
                    .content(boardWriteList.get(i).getContent()).build();

            result1.add(boardListDto);

        }

        return new PageImpl<>(result1, allBoardWriteList.getPageable(), allBoardWriteList.getTotalElements());
    }

    // 게시글 상세보기
    public BoardListDto detailWriteBoard(Long boardWriteNo){

        BoardList boardList = boardListRepository.findByNo(boardWriteNo);

        BoardListDto boardListDto = BoardListDto.builder()

                .title(boardList.getTitle())
                .boardNo(boardList.getBoard().getNo())
                .user(boardList.getUser().getNickname())
                .view(boardList.getView())
                .allowComment(boardList.isAllowComment())
                .like(boardList.getLike())
                .content(boardList.getContent()).build();

        return boardListDto;
    }

    // 생명주기 가장 짧은 Top3 뽑기
    public List<BoardDto> boardLife(){

        List<Board> boardList = boardRopository.findTop3ByOrderByBoardLifeAsc();

        List<BoardDto> result = new ArrayList<>();

//        LocalDateTime now = LocalDateTime.now();

//        System.out.println(now);

        for(int i=0;i<boardList.size();i++){

//        LocalDateTime afterTime = boardList.get(i).getBoardLife();



//            String boardLife = minus.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            BoardDto boardDto = BoardDto.builder()
                    .no(boardList.get(i).getNo())
                    .user(boardList.get(i).getUser().getNickname())
                    .title(boardList.get(i).getTitle())
                    .boardClick(boardList.get(i).getBoardClick())
                    .showName(boardList.get(i).getShowName())
                    .boardLife(ChronoUnit.DAYS.between(LocalDateTime.now(), boardList.get(i).getBoardLife()))
                    .allowDelete(boardList.get(i).isAllowDelete()).build();



            result.add(boardDto);
        }
        return result;
    }

    // 하나의 게시글에 댓글달기
    @Transactional
    public void writeComment(BoardWriteCommentDto boardWriteCommentDto){

        BoardComment boardComment = BoardComment.builder()
                .user(userRegistAndModifyRepository.findByNo(boardWriteCommentDto.getUserNo()))
                .boardList(boardListRepository.findByNo(boardWriteCommentDto.getBoardWriteNo()))
                .content(boardWriteCommentDto.getWriteComment()).build();

        boardCommentRepository.save(boardComment);
    }

    // 댓글 삭제
//    @Transactional
//    public void commentDelete(Long boardC)

    // 게시판 삭제
    @Transactional
    public void boardDelete(Long boardNo){

        Board board1 = boardRopository.findByNo(boardNo);

        board1.delete();

    }
}
