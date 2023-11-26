import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect } from 'react';

// 게시글 생성 컴포넌트

const TopDiv = styled.div`
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
const Div = styled.div``;
const TopTag = styled.div`
  font-size: 30px;
  font-weight: 580;
  margin: 50px 0 0 100px;
`;
const Tagexplain = styled.div`
  font-size: 20px;
  margin: 20px 0 50px 130px;
`;
const FlexDiv = styled.div`
  display: flex;
`;
const TopFlexDiv = styled(FlexDiv)`
  margin-left: 130px;
`;
const BottomFlexDiv = styled(FlexDiv)`
  margin-top: 100px;
  margin-left: 130px;
  justify-content: center;
`;
const TagTitle = styled.div`
  font-size: 20px;
  font-weight: 580;
  margin-top: 50px;
`;

const InputTag = styled.input`
  border-radius: 5px;
  border: 1px solid rgb(0, 0, 0, 0.3);
  box-shadow: 2px 2px rgb(0, 0, 0, 0.3);
`;
const InputTag1 = styled(InputTag)`
  width: 500px;
  height: 45px;
  margin-top: 20px;
  margin-left: 3px;
`;
const InputTag2 = styled.input`
  width: 100px;
  height: 45px;
  margin-top: 20px;
  margin-left: 3px;
`;
const InputTag3 = styled(InputTag)`
  width: 900px;
  height: 200px;
  margin-top: 20px;
  margin-left: 3px;
`;

const CreateBtn = styled.div`
  margin-right: 50px;
  width: 250px;
  height: 50px;
  background-color: #396cfa;
  border-radius: 10px;
  text-align: center;
  line-height: 50px;
  color: white;
`;

const CancelBtn = styled.div`
  width: 250px;
  height: 50px;
  background-color: #737475;
  border-radius: 10px;
  text-align: center;
  line-height: 50px;
  color: white;
`;

const MidDiv = styled.div`
  margin-left: 130px;
`;

// 게시글 생성 양식

const BoardCreateMain = () => {
  const navigate = useNavigate();
  const [content, setContent] = useState('');
  const [title, setTitle] = useState('');
  const [accept, setAcceopt] = useState(false);
  const [clickCount, setClickCount] = useState(0);
  const [userId, setUserId] = useState(0);
  // 게시판 정보
  const [urlparam, setUrlparam] = useState(0);
  const storedAccessToken = sessionStorage.getItem('accessToken');
  // 글 입력정보
  const onInput1 = (e) => {
    setTitle(e.target.value);
  };

  const onInput2 = (e) => {
    setContent(e.target.value);
  };
  const onInput3 = () => {
    setClickCount((prevCount) => prevCount + 1);
    if (clickCount % 2 === 0) {
      setAcceopt(true);
    } else {
      setAcceopt(false);
    }
  };

  // 게시판 정보 가져오기

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    for (const param of searchParams) {
      const paramName = param[0];
      console.log(paramName);
      const paramValue = parseInt(param[1], 10);
      setUrlparam(paramValue);
    }
    const BoardApi = axios.create({
      headers: { Authorization: storedAccessToken }
    });
    //유저 id정보 요청
    BoardApi.get('/util/get-userno')
      .then((res) => {
        setUserId(res.data);
      })
      .catch((err) => {
        console.log('실패1', err);
      });
  }, []);
  const CreateBoard = () => {
    const BoardApi2 = axios.create({
      headers: { Authorization: storedAccessToken }
    });
    const data = {
      allowComment: accept, // 댓글 허용여부 true ,false
      boardNo: urlparam, // 게시판 번호
      contents: content, // 내용
      title: title, // 제목
      userNo: userId // 로그인유저
    };
    //게시글 생성
    BoardApi2.post('/board/write', data)
      .then((res) => {
        console.log(res.data, '게시글 생성요청 성공');
      })
      .catch((err) => {
        console.log('실패2', err);
      });

    navigate(`/community/${urlparam}?boardNo=${urlparam}`);
  };

  return (
    <TopDiv style={{ width: '100vh', margin: '100px auto' }}>
      <TopTag>게시글 신규 등록</TopTag>
      <Tagexplain>게시글을 만들어 보세요.</Tagexplain>
      <TopFlexDiv>
        <Div>
          <TagTitle>게시글 제목</TagTitle>
          <InputTag1 onChange={onInput1}></InputTag1>
        </Div>
      </TopFlexDiv>
      <MidDiv>
        <TagTitle>게시글 내용</TagTitle>
        <InputTag3 onChange={onInput2}></InputTag3>
        <TagTitle>댓글 허용 여부</TagTitle>
        <InputTag2 type='checkbox' onChange={onInput3}></InputTag2>
      </MidDiv>

      <BottomFlexDiv>
        <CreateBtn onClick={CreateBoard}>게시글 생성하기</CreateBtn>
        <CancelBtn
          onClick={() => navigate(`/community/${urlparam}?boardNo=${urlparam}`)}
        >
          뒤로가기
        </CancelBtn>
      </BottomFlexDiv>
    </TopDiv>
  );
};

export { BoardCreateMain };
