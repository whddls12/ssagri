import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { onLogout, onLoginSuccess } from '../utils/user';

// 로그인 여부
import { useRecoilState } from 'recoil';
import { isLoggedInAtom } from '../states/account/loginAtom';
import axios from 'axios';
// 로그아웃

const NavbarDiv = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 60px;
  display: flex;
  /* justify-content: space-between; */
  z-index: 10;
  background-color: #242526;
`;

const MenuDiv = styled.div`
  width: 1500px;
  height: 7vh;
  margin-top: 5px;
  display: flex;
  /* border: 2px solid red; */
  /* margin-left: 00px; */
`;

const MenuName = styled.p`
  line-height: 1.4vh;
  font-size: 17px;
  /* margin-right: 3vw; */
  margin-left: 4.5vw;
  color: rgb(255, 255, 255, 0.5);
  transition:
    color 0.2s,
    font-weight 0.2s;
  &:hover {
    color: rgb(255, 255, 255);
    font-weight: 560;
  }
`;
const MenuName2 = styled.p`
  line-height: 1.3vh;
  font-size: 17px;
  /* margin-right: 3vw; */
  margin-left: 39.5vw;
  color: rgb(255, 255, 255, 0.5);
  transition:
    color 0.2s,
    font-weight 0.2s;
  &:hover {
    color: rgb(255, 255, 255);
    font-weight: 560;
  }
`;

const TitleTag = styled.div`
  width: 180px;
  height: 30px;
  margin-top: 4px;
  margin-left: 207px;
  position: relative;
`;

const TitleName1 = styled.span`
  font-family: var(--font-googleNanumPen);
  font-size: 44px;
  color: #4786fa; // 텍스트 색상 설정
`;
const TitleName2 = styled.span`
  font-family: var(--font-googleNanumPen);
  font-size: 44px;
  color: #f2f7f7; // 텍스트 색상 설정
`;

const NotifyDiv = styled.div`
  z-index: 50;
  position: fixed;
  bottom: 50px;
  right: 50px;
  width: 400px;
  height: 300px;
  background-color: white;
  border: 2px solid #337ccf;
  border-radius: 10px;
  font-weight: 600;
`;

const ChatNotifyDiv = styled(NotifyDiv)`
  border: 2px solid #e74118;
`;

const NotifyImg = styled.img`
  width: 150px;
  margin: 15px 0 0 110px;
`;
const NotifyTag = styled.p`
  font-size: 27px;
  margin: 30px 0 0 40px;
  color: #337ccf;
`;
const ChatNotifyTag = styled.p`
  font-size: 27px;
  margin: 30px 0 0 40px;
  color: #e74118;
`;

const NotifyText = styled.p`
  font-size: 27px;
  margin: 5px 0 0 140px;
  color: #337ccf;
`;
const CancleBtn = styled.div`
  width: 40px;
  height: 30px;
  font-size: 20px;
  border-radius: 5px;
  text-align: center;
  line-height: 30px;
  position: absolute;
  top: 0;
  right: 0;
  &:hover {
    background-color: #e4e3e3; /* 호버 시 변경될 배경색 */
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
`;
const GoTradeBtn = styled.div`
  width: 140px;
  height: 40px;
  /* border: 1px solid black; */
  border-radius: 15px;
  text-align: center;
  line-height: 40px;
  margin-top: 10px;
  margin-left: 120px;
  color: white;
  background-color: #337ccf;
  &:hover {
    background-color: #6faabb; /* 호버 시 변경될 배경색 */
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
`;
const GoChatBtn = styled(GoTradeBtn)`
  background-color: #f15e3a;
  &:hover {
    background-color: #ee4b22; /* 호버 시 변경될 배경색 */
    cursor: pointer; /* 호버 시 커서 모양 변경 (선택 사항) */
  }
`;

const UserTag = styled.div`
  font-size: 13px;
  position: absolute;
  top: 65px;
  left: 110px;
`;

const LogoImg = styled.img`
  width: 26px;
  position: absolute;
  top: 11px;
  left: 80px;
  z-index: -1;
`;

const ChattingBtn = styled.div`
  cursor: pointer;
  position: fixed;
  right: 20px;
  bottom: 20px;

  width: 100px;
  height: 100px;
  border-radius: 70%;
  background-color: rgb(157, 198, 255, 0.7);

  display: flex;
  justify-content: center;
  align-items: center;
`;

const Notify = ({ setNotify, bidderNickname, price, auctionNo }) => {
  const navigate = useNavigate();
  return (
    <NotifyDiv>
      <CancleBtn onClick={() => setNotify(false)}>x</CancleBtn>
      <NotifyTag>경매상회입찰이 되었어요!</NotifyTag>
      <NotifyImg src='/assets/img/notify.PNG'></NotifyImg>
      <NotifyText>+ {price}</NotifyText>
      {/* <GoTradeBtn onClick={() => navigate('/')}>재입찰하러 가기</GoTradeBtn> */}
      <UserTag>{bidderNickname}님이 입찰하셨습니다.</UserTag>

      <GoTradeBtn
        onClick={() => {
          setNotify(false);
          navigate(`/auctionDetail/${auctionNo}`);
        }}
      >
        재입찰하러 가기
      </GoTradeBtn>
    </NotifyDiv>
  );
};
const ChatNotify = ({ setChatNotify, chatNickname }) => {
  const navigate = useNavigate();
  return (
    <ChatNotifyDiv>
      <CancleBtn onClick={() => setChatNotify(false)}>x</CancleBtn>
      <ChatNotifyTag>{chatNickname}님이 메시지를 보냈어요!</ChatNotifyTag>
      <NotifyImg
        src='/assets/img/ChaTArm.PNG'
        style={{ opacity: 0.7 }}
      ></NotifyImg>
      {/* <NotifyText>+ {price}</NotifyText> */}
      {/* <GoTradeBtn onClick={() => navigate('/')}>재입찰하러 가기</GoTradeBtn> */}
      {/* <UserTag>{bidderNickname}님이 입찰하셨습니다.</UserTag> */}

      <GoChatBtn
        onClick={() => {
          setChatNotify(false);
          navigate(`/chat?sellorNo=${2}`);
        }}
      >
        채팅하러 가기
      </GoChatBtn>
    </ChatNotifyDiv>
  );
};

const Title = () => {
  const navigate = useNavigate();

  const goMain = () => {
    navigate('/');
  };
  return (
    <TitleTag onClick={goMain}>
      <LogoImg src='/assets/img/logoImg.png'></LogoImg>
      <TitleName1>싸</TitleName1>
      <TitleName2>그리</TitleName2>
    </TitleTag>
  );
};

const MenuBar = () => {
  const [isLoggedIn, setIsLoggedIn] = useRecoilState(isLoggedInAtom);
  const [selectedMenu, setSelectedMenu] = useState('');

  const navigate = useNavigate();

  const [showOverlay, setShowOverlay] = useState(false);
  // MenuName에 마우스 진입 이벤트 핸들러
  const handleMouseEnter = () => {
    setShowOverlay(true);
  };

  // MenuName에서 마우스를 떠남 이벤트 핸들러
  const handleMouseLeave = () => {
    setShowOverlay(false);
  };

  const goLogout = () => {
    onLogout();
    setIsLoggedIn(false);
    localStorage.removeItem('isLoggedIn');
  };

  const goLogin = () => {
    setSelectedMenu('로그인페이지');
    navigate('/login');
  };
  const goMain = () => {
    setSelectedMenu('메인페이지');
    navigate('/');
  };
  const goTrade = () => {
    setSelectedMenu('거래페이지');
    navigate('/tradeMain');
  };
  const goAuction = () => {
    setSelectedMenu('경매페이지');
    navigate('/auction');
  };
  const goCommu = () => {
    setSelectedMenu('커뮤니티페이지');
    navigate('/community');
  };
  const goMyChat = () => {
    const userNo = localStorage.getItem('userNo');
    navigate(`/chat/?sellorNo=${userNo}`);
  };

  useEffect(() => {
    console.log('로그인 여부 바뀜', isLoggedIn);
  }, [isLoggedIn]);

  return (
    <MenuDiv>
      <MenuName
        onClick={goMain}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        style={{
          color:
            selectedMenu === '메인페이지' ? 'white' : 'rgb(255, 255, 255, 0.5)'
        }}
      >
        메인페이지
      </MenuName>
      <MenuName
        onClick={goTrade}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        style={{
          color:
            selectedMenu === '거래페이지' ? 'white' : 'rgb(255, 255, 255, 0.5)'
        }}
      >
        중고거래{' '}
      </MenuName>
      <MenuName
        onClick={goAuction}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        style={{
          color:
            selectedMenu === '경매페이지' ? 'white' : 'rgb(255, 255, 255, 0.5)'
        }}
      >
        경매{' '}
      </MenuName>
      <MenuName
        onClick={goCommu}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        style={{
          color:
            selectedMenu === '커뮤니티페이지'
              ? 'white'
              : 'rgb(255, 255, 255, 0.5)'
        }}
      >
        커뮤니티{' '}
      </MenuName>
      <div
        style={{
          position: 'absolute',
          top: '100%',
          left: 0,
          width: '100vw',
          height: showOverlay ? '10px' : 0,
          backgroundColor: '#242526',
          transition: 'height 0.7s' // 이 부분 수정
        }}
      ></div>
      {isLoggedIn ? (
        <MenuName2
          onClick={goLogout}
          onMouseEnter={handleMouseEnter}
          onMouseLeave={handleMouseLeave}
        >
          로그아웃
        </MenuName2>
      ) : (
        <MenuName2
          onClick={goLogin}
          onMouseEnter={handleMouseEnter}
          onMouseLeave={handleMouseLeave}
        >
          로그인
        </MenuName2>
      )}
      {isLoggedIn ? (
        <ChattingBtn
          onClick={() => {
            goMyChat();
          }}
        >
          <img
            style={{ width: '70px', height: '70px' }}
            src={'/assets/img/chat-icon.png'}
            alt='채팅 아이콘'
          />
        </ChattingBtn>
      ) : null}
    </MenuDiv>
  );
};

// const SideDiv = styled.div`
//   width: 20vw;
//   height: 7vh;
//   /* border: 2px solid blue; */
//   display: flex;
//   justify-content: center;
//   align-items: center;
// `;

// const SideName = styled.p`
//   font-size: 1.1vw;
//   margin-right: 2vw;
// `;

// const SideBar = () => {
//   return (
//     <SideDiv>
//       <SideName>마이페이지</SideName>
//       <SideName>햄버그바</SideName>
//     </SideDiv>
//   );
// };

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useRecoilState(isLoggedInAtom);
  // 알람유무
  const [notify, setNotify] = useState(false);
  const [chatNotify, setChatNotify] = useState(false);
  const [price, setPrice] = useState(0);
  const [auctionNo, setauctionNo] = useState(0);
  const [bidderNickname, setbidderNickname] = useState('');
  const [chatNickname, setChatNickname] = useState('');

  const onSilentRefreshInNav = () => {
    if (isLoggedIn) {
      localStorage.removeItem('isLoggedIn');
      axios
        .get('/jwt/refill')
        .then((res) => {
          console.log('silent refresh, 새로운 액세스 토큰 발급');
          console.log('Recoil 로그인 여부: ', isLoggedIn);
          // 리프레시 토큰이 유효 [ STATUS 200 ]
          // 새로운 액세스 토큰 발급
          onLoginSuccess(res);
          setIsLoggedIn(true);
        })
        .catch(() => {
          console.log('silent refresh, 리프레시 토큰이 유효하지 않습니다.');
          // 리프레시 토큰이 유효하지 않은 경우 [ STATUS 400, 500 ]
          // 로그인페이지로 이동
        });
    }
  };

  useEffect(() => {
    if (isLoggedIn) {
      console.log('로그인 성공@@@ : 알림 시작');
      const userId = localStorage.getItem('userNo');
      onSilentRefreshInNav();
      // 알림기능
      const urlEndPoint = `https://j9b209.p.ssafy.io/api/notification/subscribe/${userId}`;
      const eventSource = new EventSource(urlEndPoint);
      eventSource.addEventListener('sse-emitter-created', function (event) {
        console.log('소켓 연결', event);
      });

      eventSource.addEventListener('new bid', function (e) {
        if (!notify) {
          const eventData = JSON.parse(e.data);
          setPrice(eventData.price);
          setauctionNo(eventData.auctionNo);
          setbidderNickname(eventData.bidderNickname);
          setNotify(true);
        }
        console.log('경매 알림 :', JSON.parse(e.data));
      });
      eventSource.addEventListener('new chat', function (e) {
        if (!notify) {
          const eventChatData = JSON.parse(e.data);
          setChatNickname(eventChatData.senderNickname);
          // setauctionNo(e.data.auctionNo);
          // setbidderNickname(e.data.bidderNickname);
          setChatNotify(true);
        }
        console.log('채팅 알림 :', e.data);
      });
    }
  }, [isLoggedIn]);

  return (
    <NavbarDiv>
      <Title></Title>
      <MenuBar></MenuBar>
      {/* <SideBar></SideBar> */}
      {/* 알림 */}
      {notify ? (
        <Notify
          setNotify={setNotify}
          price={price}
          auctionNo={auctionNo}
          bidderNickname={bidderNickname}
        ></Notify>
      ) : null}
      {chatNotify ? (
        <ChatNotify
          setChatNotify={setChatNotify}
          chatNickname={chatNickname}
        ></ChatNotify>
      ) : null}
    </NavbarDiv>
  );
};

export default Navbar;
