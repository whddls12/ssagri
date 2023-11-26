import { AuctionPage } from '../../components/auctionStyle/auctionPage.styles';
import { AuctionHeader } from '../../components/header';
import { isLoggedInAtom } from '../../states/account/loginAtom';
import { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';

const auctionPage = () => {
  // 로그인 여부
  const isLoggedIn = useRecoilValue(isLoggedInAtom);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoggedIn) {
      alert('로그인이 필요한 페이지입니다.');
      navigate('/login');
    }
  }, [isLoggedIn]);
  return (
    <div>
      <AuctionHeader></AuctionHeader>

      <AuctionPage></AuctionPage>
    </div>
  );
};

export default auctionPage;
