import './App.css';
import { Route, Routes } from 'react-router-dom';

// 각 페이지 정보
import LoginPage from './pages/loginPage';
import MainPage from './pages/mainPage/mainPage';
import TradeMainPage from './pages/tradePage/tradeMainPage';
import TradeListPage from './pages/tradePage/tradeListPage';
import TradeCreatePage from './pages/tradePage/tradeCreatePage';
import TradeUpdatePage from './pages/tradePage/tradeUpdatePage';
import TradeDetailPage from './pages/tradePage/tradeDetailPage';
import ChattingPage from './pages/chattingPage';
import CommunityPage from './pages/communityPage/communityPage';
import CommunityCreatePage from './pages/communityPage/communityCreatePage';
import BoardListPage from './pages/communityPage/boardListPage';
import BoardCreatePage from './pages/communityPage/boardCreatePage';
import BoardDetailPage from './pages/communityPage/boardDetailPage';
import AuctionPage from './pages/auctionPage/auctionPage';
import AuctionDetailPage from './pages/auctionPage/auctionDetailPage';
import AuctionCreatePage from './pages/auctionPage/auctionCreatePage';
import Navbar from './components/navbar';
import MyPagePage from './pages/mypagePage';

import { useEffect } from 'react';
import { useRecoilState } from 'recoil';
import { isLoggedInAtom } from './states/account/loginAtom';
import SocialKakaoPage from './pages/SocialKakao';

const App = () => {
  // @ts-ignore
  const [isLoggedIn, setIsLoggedIn] = useRecoilState(isLoggedInAtom);

  useEffect(() => {
    const storedIsLoggedIn = localStorage.getItem('isLoggedIn');
    if (storedIsLoggedIn === 'true') {
      setIsLoggedIn(true);
    }
  }, [setIsLoggedIn]);

  return (
    <div>
      <Navbar></Navbar>
      <Routes>
        {/* 로그인 페이지 */}
        <Route path='/login' element={<LoginPage />} />
        {/* 마이 페이지 */}
        <Route path='/mypage' element={<MyPagePage />} />
        {/* 카카오 로그인 중간 페이지 */}
        <Route path='/oauth/callback/kakao' element={<SocialKakaoPage />} />
        {/* 메인 페이지 */}
        <Route path='/' element={<MainPage />} />
        {/* 중고거래 메인 페이지 */}
        <Route path='/tradeMain' element={<TradeMainPage />} />
        {/* 중고거래 페이지 */}
        <Route path='/tradeList' element={<TradeListPage />} />
        {/* 중고거래 생성 페이지 */}
        <Route path='/tradeCreate' element={<TradeCreatePage />} />
        {/* 중고거래 수정 페이지 */}
        <Route path='/tradeUpdate' element={<TradeUpdatePage />} />
        {/* 중고거래 상세 페이지 */}
        <Route path='/tradeDetail/:no' element={<TradeDetailPage />} />
        {/* 채팅 화면 페이지 */}
        <Route path='/chat' element={<ChattingPage />} />
        {/* 경매 페이지 */}
        <Route path='/auction' element={<AuctionPage />} />
        {/* 경매 생성 페이지 */}
        <Route path='/auctionCreate' element={<AuctionCreatePage />} />
        {/* 경매 상세 페이지 */}
        <Route path='/auctionDetail/:no' element={<AuctionDetailPage />} />
        {/* 커뮤티니 페이지 */}
        <Route path='/community' element={<CommunityPage />} />
        {/* 커뮤티니 생성 */}
        <Route path='/communityCreate' element={<CommunityCreatePage />} />
        {/* 커뮤티니 게시판 페이지 */}
        <Route path='/community/:no' element={<BoardListPage />} />
        {/* 커뮤티니 게시판 생성 */}
        <Route path='/community/:no/Create' element={<BoardCreatePage />} />
        {/* 커뮤티니 게시판 디테일 페이지 */}
        <Route path='/community/:no/Detail/' element={<BoardDetailPage />} />
      </Routes>
    </div>
  );
};

export default App;
