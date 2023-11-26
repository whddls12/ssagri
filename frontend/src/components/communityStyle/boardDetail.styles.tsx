import { styled } from 'styled-components';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// 게시글 세부페이지 컴포넌트

const DetailDiv = styled.div`
  width: 100%;
  height: 1000px;
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
const TopTitle = styled.div`
  width: 100%;
  height: 200px;
  display: flex;
  background-image: url('/assets/img/boardTop.PNG');
  background-size: cover;
  background-position: -510px 0px;
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
const BoardImg = styled.img`
  width: 60px;
  height: 60px;
  margin-right: 10px;
`;

const TopDiv = styled.div`
  width: 1100px;
  height: 100px;
  border: 2px solid #f5ddae;
  margin: 50px auto;
  line-height: 100px;
  font-size: 25px;
  font-weight: 600;
  border-radius: 5px;
  background-color: #f6e1b6;
  box-shadow: 2px 2px 2px #f5ddae;
`;
const MidDiv = styled.div`
  width: 1100px;
  height: 450px;
  border: 2px solid #fae4bf;
  background-color: #fce9c7;
  box-shadow: 2px 2px 2px #f5ddae;
  margin: 50px auto;
  font-size: 20px;
  border-radius: 5px;
`;

const TopTag = styled.div`
  margin-left: 50px;
`;
const MidTag = styled.div`
  margin-left: 55px;
  margin-top: 20px;
`;
const TagBtn = styled.div`
  width: 250px;
  height: 50px;
  background-color: rgb(115, 116, 117, 0.7);
  border-radius: 10px;
  text-align: center;
  line-height: 50px;
  color: white;
  margin-left: 1230px;
  &:hover {
    background-color: rgb(115, 116, 117); /* 호버 시 변경될 배경색 */
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
`;

const BottomTag = styled.div`
  margin: 200px;
`;

// 게시글 디테일 양식

const BoardDetailMain = () => {
  const [urlparam, setUrlparam] = useState(0);
  const storedAccessToken = sessionStorage.getItem('accessToken');

  const [listNo, setListNo] = useState(1);

  type board = {
    title: string;
    content: string;
    user: string;
  };

  // const [boardData, setBoardData] = useState([]);
  const [boardData, setBoardData] = useState<board | null>(null);

  const navigate = useNavigate();
  const GoBack = () => {
    navigate(`/community/${urlparam}?boardNo=${urlparam}`);
  };
  // 게시글 정보 가져오기
  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const paramValue = searchParams.get('boardNo');
    const paramListValue = searchParams.get('ListNo');

    if (paramValue !== null) {
      setUrlparam(parseInt(paramValue, 10)); // 가져온 값이 문자열이므로 숫자로 변환 후 설정
    }
    if (paramListValue !== null) {
      setListNo(parseInt(paramListValue, 10)); // 가져온 값이 문자열이므로 숫자로 변환 후 설정
    }

    const BoardListApi = axios.create({
      headers: { Authorization: storedAccessToken }
    });

    //유저 id정보 요청
    BoardListApi.get(`/board/write-list-detail/${listNo}`)
      .then((res) => {
        setBoardData(res.data);
      })
      .catch((err) => {
        console.log('실패1', err);
      });

    // 게시글 조회수 증가
    BoardListApi.get(`/board/boardList-click/${listNo}`)
      .then(() => {
        console.log('조회수증가');
      })
      .catch((err) => {
        console.log('조회수증가 실패', err);
      });
  }, []);

  return (
    <DetailDiv>
      <TopTitle>
        <TitleDiv>
          <BoardImg src='/assets/img/boardImg.png'></BoardImg>
          질문게시판
        </TitleDiv>
      </TopTitle>
      <TopDiv>
        <TopTag>글 제목 : {boardData?.title}</TopTag>
      </TopDiv>
      <MidDiv>
        <MidTag>
          글 내용 :
          <br />
          <br />
          {boardData?.content}
        </MidTag>
        <BottomTag>작성자 : {boardData?.user}</BottomTag>
      </MidDiv>
      <TagBtn onClick={GoBack}>뒤로가기</TagBtn>
    </DetailDiv>
  );
};

export { BoardDetailMain };
