import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

// 게시글 목록리스트 구성 컴포넌트

const DetailDiv = styled.div`
  width: 100%;
  height: 1000px;
`;

const TopTitle = styled.div`
  width: 100%;
  height: 200px;
  display: flex;
  background-image: url('/assets/img/boardTop.PNG');
  background-size: cover;
  background-position: -510px 0px;
  animation: fadein 0.7s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const TopTag = styled.div`
  width: 100%;
  height: 150px;

  display: flex;
  animation: fadein 1s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const LineDiv = styled.hr`
  width: 1750px;
`;

const FlexDiv = styled.div`
  display: flex;
`;

const LeftDiv = styled.div`
  width: 100px;
  margin-left: 300px;
`;
const RightDiv = styled.div`
  width: 100px;
  margin: 50px 0 0 600px;
`;
const CreateDiv = styled.div`
  width: 600px;
  height: 80px;
  background-color: #f6e1b6;
  margin: 50px 0 50px 0;
  border-radius: 7px;
  box-shadow: 2px 2px 2px rgb(0, 0, 0, 0.4);
  line-height: 70px;
  font-size: 25px;
  font-weight: 600;
  line-height: 80px;
  &:hover {
    background-color: #f1daab; /* 호버 시 변경될 배경색 */
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
  animation: fadein 1.4s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const CreateTag = styled.div`
  margin-left: 90px;
`;

const CommuDiv = styled.div`
  width: 600px;
  border: 1px solid rgb(0, 0, 0, 0.2);
  box-shadow: 2px 2px 2px rgb(0, 0, 0, 0.4);
  margin-bottom: 40px;
  border-radius: 5px;
`;
const CommuBody1 = styled(CommuDiv)`
  height: 350px;
  &:hover {
    border: 1px solid rgb(0, 0, 0, 0.4);
    box-shadow: 2.5px 2.5px 2.5px rgb(0, 0, 0, 0.4);
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
  animation: fadein 2s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const CommuBody2 = styled(CommuDiv)`
  height: 600px;
  &:hover {
    border: 1px solid rgb(0, 0, 0, 0.4);
    box-shadow: 2.5px 2.5px 2.5px rgb(0, 0, 0, 0.4);
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }

  animation: fadein 2.3s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const CommuBody3 = styled(CommuDiv)`
  height: 300px;
  margin-top: 50px;
  &:hover {
    border: 1px solid rgb(0, 0, 0, 0.4);
    box-shadow: 2.5px 2.5px 2.5px rgb(0, 0, 0, 0.4);
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
  animation: fadein 2.5s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const CommuBody4 = styled(CommuDiv)`
  height: 400px;
  &:hover {
    border: 1px solid rgb(0, 0, 0, 0.4);
    box-shadow: 2.5px 2.5px 2.5px rgb(0, 0, 0, 0.4);
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
  animation: fadein 2.8s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const CommuBody5 = styled(CommuDiv)`
  height: 300px;
  &:hover {
    border: 1px solid rgb(0, 0, 0, 0.4);
    box-shadow: 2.5px 2.5px 2.5px rgb(0, 0, 0, 0.4);
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
  animation: fadein 3s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

// 페이징
const PagingSpace = styled.div`
  width: 50%;
  height: 50px;
  margin-top: 50px;
  margin-left: 490px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const PagingButton = styled.button`
  width: 40px;
  height: 40px;
  margin: 0 2px;
  border: 1px solid #4786fa;
  border-radius: 5px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  &:hover {
    box-shadow: 1px 1px 2px 2px #757575;
  }
`;
const PagingButtonText = styled.div`
  height: 30px;
  font-size: 20px;
  font-weight: bold;
`;

const Alarm = styled.div`
  width: 120px;
  height: 40px;
  text-align: center;
  line-height: 40px;
  border: 2px solid #e7d2a7;
  box-shadow: 2px 2px 2px rgb(0, 0, 0, 0);
  margin: 50px 0 0 100px;
  font-size: 20px;
  background-color: #f6e1b6;
  display: flex;
  border-radius: 5px;
  &:hover {
    background-color: #f1daab; /* 호버 시 변경될 배경색 */
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
`;

const AlarmImg = styled.img`
  width: 25px;
  height: 25px;
  margin-top: 8px;
  margin-left: 10px;
  margin-right: 3px;
`;

const AlarmTag = styled.div``;

const TitleDiv = styled.div`
  font-family: var(--font-googleNanumPen);
  display: flex;
  font-size: 45px;
  margin-top: 104px;
  margin-left: 70px;
  width: 700px;
  color: white;
  height: 50px;
`;

const LifeDiv = styled.div`
  font-family: var(--font-googleNanumPen);
  font-size: 30px;
  margin-top: 110px;
  margin-left: 860px;
  color: rgb(255, 0, 0, 0.6);
  width: 200px;
  height: 50px;
  background-color: rgb(246, 225, 182, 0.3);
  text-align: center;
  line-height: 50px;
`;

const BoardImg = styled.img`
  width: 60px;
  height: 60px;
  margin-right: 10px;
`;
const BoardImg2 = styled.img`
  width: 80px;
  height: 80px;
  margin-left: 680px;
  margin-top: 35px;
`;

const Toptags = styled.span`
  font-family: var(--font-googleNanumPen);
  font-size: 29px;
  margin: 12px 0 0 30px;
`;

const BodyTop = styled.div`
  display: flex;
  width: 590px;
  height: 70px;
  line-height: 70px;
  margin-top: 5px;
`;

const Profile = styled.img`
  width: 37px;
  height: 37px;
  border-radius: 50%;
  margin: 18px 20px 0 20px;
`;
const NickName = styled.div`
  font-size: 20px;
  margin-right: 70px;
  width: 500px;

  overflow: hidden;
`;

const Times = styled.div`
  color: rgb(0, 0, 0, 0.5);
  overflow: hidden;
`;
const MoreImg = styled.img``;

const BodyTitle = styled.div`
  width: 490px;
  height: 50px;
  line-height: 50px;
  margin-left: 30px;
  margin-top: 10px;
  margin-bottom: 15px;
  font-weight: 600;
  font-size: 20px;
`;
const BodyTitle2 = styled.div`
  width: 490px;
  height: 50px;
  line-height: 50px;
  margin-left: 30px;
  margin-top: 20px;
  margin-bottom: 15px;
  font-weight: 600;
  font-size: 20px;
`;
const BodyCommu = styled.div`
  width: 490px;
  height: 140px;
  margin-left: 38px;
  margin-top: 10px;
`;
const BodyCommu2 = styled.div`
  width: 490px;
  height: 358px;
  margin-left: 38px;
  margin-top: 30px;
`;
const BodyCommu3 = styled.div`
  width: 490px;
  height: 82px;
  margin-left: 38px;
  margin-top: 10px;
`;
const BodyCommu4 = styled.div`
  width: 490px;
  height: 188px;
  margin-left: 38px;
  margin-top: 10px;
`;
const BodyCommu5 = styled.div`
  width: 490px;
  height: 88px;
  margin-left: 38px;
  margin-top: 10px;
`;
const BodyLine = styled.hr`
  width: 550px;
  margin-bottom: 14px;
`;
const BodyBottom = styled.div`
  width: 590px;
  height: 40px;
  display: flex;
`;
const Views = styled.div`
  margin-left: 45px;
`;
const Likes = styled.div`
  margin-left: 360px;
`;

const Heart = styled.img`
  width: 25px;
  height: 25px;
  margin: -4px 0 0 20px;
  z-index: 1000;
`;

// 게시글 리스트 구성 양식

const BoardMain = () => {
  const navigate = useNavigate();
  const GoBoard = (no) => {
    navigate(`/community/${urlparam}/Detail?boardNo=${urlparam}?ListNo=${no}`);
  };
  type boardItem = {
    title: string;
    user: string;
    writeTime: string;
    content: string;
    view: number;
    like: number;
    no: number;
  };

  const [urlparam, setUrlparam] = useState(0);
  const [board1, setBoard1] = useState<boardItem | null>(null);
  const [board2, setBoard2] = useState<boardItem | null>(null);
  const [board3, setBoard3] = useState<boardItem | null>(null);
  const [board4, setBoard4] = useState<boardItem | null>(null);
  const [board5, setBoard5] = useState<boardItem | null>(null);
  const [boardView, setboardView] = useState(0);
  const [boardLife, setboardLife] = useState(0);

  const [totalPages, setTotalPages] = useState(0);
  const [pickNum, setPickNum] = useState(0);
  const [likes, setLikes] = useState(false);
  const [selectedPage, setSelectedPage] = useState(null);
  const storedAccessToken = sessionStorage.getItem('accessToken');
  const checkLike = () => {
    if (likes) {
      setLikes(false);
    } else {
      setLikes(true);
    }
  };

  const ChangePage = (index) => {
    setPickNum(index);
    setSelectedPage(index);
  };

  // 게시판 정보 가져오기

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    for (const param of searchParams) {
      const paramValue = parseInt(param[1], 10);
      setUrlparam(paramValue);
      const BoardListApi = axios.create({
        headers: { Authorization: storedAccessToken }
      });
      //유저 id정보 요청
      BoardListApi.get(
        `/board/all-write-list/${paramValue}?page=${pickNum}&size=5`
      )
        .then((res) => {
          setBoard1(res.data.content[0]);
          setBoard2(res.data.content[1]);
          setBoard3(res.data.content[2]);
          setBoard4(res.data.content[3]);
          setBoard5(res.data.content[4]);
          setTotalPages(res.data.totalPages);
        })
        .catch((err) => {
          console.log('실패1', err);
        });
      // 게시판 데이터 요청
      BoardListApi.get(`/board/all-write-list-bar/${paramValue}`)
        .then((res) => {
          setboardView(res.data.boardClick);
          setboardLife(res.data.boardLife);
        })

        .catch((err) => {
          console.log('실패1', err);
        });
    }
  }, [likes, pickNum]);

  return (
    <DetailDiv>
      <TopTitle>
        <TitleDiv>
          <BoardImg src='/assets/img/boardImg.png'></BoardImg>
          질문게시판
          <Toptags>| 관심이용자수 {boardView}</Toptags>
        </TitleDiv>
        <LifeDiv>남은 수명 : {boardLife} Day</LifeDiv>
      </TopTitle>
      <TopTag>
        <Alarm>
          <AlarmImg src='/assets/img/alarm.png'></AlarmImg>
          <AlarmTag>알림받기</AlarmTag>
        </Alarm>
        <BoardImg2 src='/assets/img/boardImg.png'></BoardImg2>
      </TopTag>
      <LineDiv></LineDiv>
      <FlexDiv>
        <LeftDiv>
          <CreateDiv
            onClick={() =>
              navigate(`/community/${urlparam}/Create?boardNo=${urlparam}`)
            }
          >
            <CreateTag>글 생성하기</CreateTag>
          </CreateDiv>
          {board1 ? (
            <CommuBody1 onClick={() => GoBoard(board1.no)}>
              <BodyTop>
                <Profile src='/assets/img/profile.png'></Profile>
                <NickName>{board1.user}</NickName>
                <Times>{board1.writeTime}</Times>
                <MoreImg></MoreImg>
              </BodyTop>
              <BodyTitle>{board1.title}</BodyTitle>
              <BodyCommu>{board1.content}</BodyCommu>
              <BodyLine></BodyLine>
              <BodyBottom>
                <Views>{board1.view} Views</Views>
                <Likes>{board1.like} 좋아요</Likes>
                {likes ? (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartColor.png'
                  ></Heart>
                ) : (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartWhite.png'
                  ></Heart>
                )}
              </BodyBottom>
            </CommuBody1>
          ) : null}
          {board2 ? (
            <CommuBody2 onClick={() => GoBoard(board2.no)}>
              <BodyTop>
                <Profile src='/assets/img/profile.png'></Profile>
                <NickName>{board2.user}</NickName>
                <Times>{board2.writeTime}</Times>
                <MoreImg></MoreImg>
              </BodyTop>
              <BodyTitle2>{board2.title}</BodyTitle2>
              <BodyCommu2>{board2.content}</BodyCommu2>
              <BodyLine></BodyLine>
              <BodyBottom>
                <Views>{board2.view} Views</Views>
                <Likes>{board2.like} 좋아요</Likes>
                {likes ? (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartColor.png'
                  ></Heart>
                ) : (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartWhite.png'
                  ></Heart>
                )}
              </BodyBottom>
            </CommuBody2>
          ) : null}
        </LeftDiv>
        <RightDiv>
          {board3 ? (
            <CommuBody3 onClick={() => GoBoard(board3.no)}>
              <BodyTop>
                <Profile src='/assets/img/profile.png'></Profile>
                <NickName>{board3.user}</NickName>
                <Times>{board3.writeTime}</Times>
                <MoreImg></MoreImg>
              </BodyTop>
              <BodyTitle>{board3.title}</BodyTitle>
              <BodyCommu3>{board3.content}</BodyCommu3>
              <BodyLine></BodyLine>
              <BodyBottom>
                <Views>{board3.view} Views</Views>
                <Likes>{board3.like} 좋아요</Likes>

                {likes ? (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartColor.png'
                  ></Heart>
                ) : (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartWhite.png'
                  ></Heart>
                )}
              </BodyBottom>
            </CommuBody3>
          ) : null}
          {board4 ? (
            <CommuBody4 onClick={() => GoBoard(board4.no)}>
              <BodyTop>
                <Profile src='/assets/img/profile.png'></Profile>
                <NickName>{board4.user}</NickName>
                <Times>{board4.writeTime}</Times>
                <MoreImg></MoreImg>
              </BodyTop>
              <BodyTitle>{board4.title}</BodyTitle>
              <BodyCommu4>{board4.content}</BodyCommu4>
              <BodyLine></BodyLine>
              <BodyBottom>
                <Views>{board4.view} Views</Views>
                <Likes>{board4.like} 좋아요</Likes>
                {likes ? (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartColor.png'
                  ></Heart>
                ) : (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartWhite.png'
                  ></Heart>
                )}
              </BodyBottom>
            </CommuBody4>
          ) : null}
          {board5 ? (
            <CommuBody5 onClick={() => GoBoard(board5.no)}>
              <BodyTop>
                <Profile src='/assets/img/profile.png'></Profile>
                <NickName>{board5.user}</NickName>
                <Times>{board5.writeTime}</Times>
                <MoreImg></MoreImg>
              </BodyTop>
              <BodyTitle>{board5.title}</BodyTitle>
              <BodyCommu5>{board5.content}</BodyCommu5>
              <BodyLine></BodyLine>
              <BodyBottom>
                <Views>{board5.view} Views</Views>
                <Likes>{board5.like} 좋아요</Likes>
                {likes ? (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartColor.png'
                  ></Heart>
                ) : (
                  <Heart
                    onClick={checkLike}
                    src='/assets/img/heartWhite.png'
                  ></Heart>
                )}
              </BodyBottom>
            </CommuBody5>
          ) : null}
        </RightDiv>
      </FlexDiv>

      <PagingSpace>
        <PagingButton>
          <PagingButtonText>&lt;&lt;</PagingButtonText>
        </PagingButton>
        <PagingButton>
          <PagingButtonText>&lt;</PagingButtonText>
        </PagingButton>
        {Array.from({ length: totalPages }, (_, index) => (
          <PagingButton
            key={index + 1}
            onClick={() => {
              ChangePage(index);
            }}
            style={{
              backgroundColor: selectedPage === index ? 'green' : 'initial',
              color: selectedPage === index ? 'white' : 'black'
            }}
          >
            <PagingButtonText>{index + 1}</PagingButtonText>
          </PagingButton>
        ))}

        <PagingButton>
          <PagingButtonText>&gt;</PagingButtonText>
        </PagingButton>
        <PagingButton>
          <PagingButtonText>&gt;&gt;</PagingButtonText>
        </PagingButton>
      </PagingSpace>
    </DetailDiv>
  );
};

export { BoardMain };
