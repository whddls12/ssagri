import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

// 게시판 생성 컴포넌트

const TopDiv = styled.div`
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
const InputTag2 = styled(InputTag)`
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

const InputCheckbox = styled.input``;

// 게시판 생성 양식

const CommuCreate = () => {
  const [commuTitle, setCommuTitle] = useState('');
  const [commuContent, setCommuContent] = useState('');
  const [anony, setAnony] = useState('실명');
  const [userId, setUserId] = useState(0);
  const [clickCount, setClickCount] = useState(0);

  const navigate = useNavigate();
  const storedAccessToken = sessionStorage.getItem('accessToken');

  const CreateCommu = () => {
    const CommuApi = axios.create({
      headers: { Authorization: storedAccessToken }
    });
    const data = {
      comment: commuContent,
      title: commuTitle,
      userNo: userId,
      who: anony
    };
    //게시판 순위 정보
    CommuApi.post('/board/regist', data)
      .then((res) => {
        console.log(res.data, '1번요청성공');
      })
      .catch((err) => {
        console.log('실패1', err);
      });
    navigate('/community');
  };

  const onInput1 = (e) => {
    setCommuTitle(e.target.value);
  };
  const onInput2 = (e) => {
    setCommuContent(e.target.value);
  };
  const onInput3 = () => {
    setClickCount((prevCount) => prevCount + 1);
    if (clickCount % 2 === 0) {
      setAnony('익명');
    } else {
      setAnony('실명');
    }
  };

  useEffect(() => {
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

  return (
    <TopDiv style={{ width: '100vh', margin: '0 auto' }}>
      <TopTag>게시판 신규 등록</TopTag>
      <Tagexplain>게시판 설정 정보를 관리 할 수 있습니다.</Tagexplain>
      <TopFlexDiv>
        <Div>
          <TagTitle>게시판 제목</TagTitle>
          <InputTag1 onChange={onInput1}></InputTag1>
        </Div>
        <Div>
          <TagTitle>게시판 색상</TagTitle>
          <InputTag2></InputTag2>
        </Div>
      </TopFlexDiv>
      <MidDiv>
        <TagTitle>게시판 설명</TagTitle>
        <InputTag3 onChange={onInput2}></InputTag3>
        <TagTitle>익명 여부</TagTitle>
        <InputCheckbox type='checkbox' onChange={onInput3}></InputCheckbox>
        {anony}
        <TagTitle>예상 게시판 이미지 미리보기</TagTitle>
        <InputTag2></InputTag2>
      </MidDiv>

      <BottomFlexDiv>
        <CreateBtn onClick={CreateCommu}>게시판 생성하기</CreateBtn>
        <CancelBtn onClick={() => navigate('/community')}>뒤로가기</CancelBtn>
      </BottomFlexDiv>
    </TopDiv>
  );
};

export { CommuCreate };
