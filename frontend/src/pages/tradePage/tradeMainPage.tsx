import { TradeMain } from '../../components/tradeStyle/tradeMainPage.styles';
import { useEffect } from 'react';
import { isLoggedInAtom } from '../../states/account/loginAtom';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';

const TradeMainPage = () => {
  // 로그인 여부
  const isLoggedIn = useRecoilValue(isLoggedInAtom);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoggedIn) {
      alert('로그인이 필요한 페이지입니다.');
      navigate('/login');
    }
  }, [isLoggedIn]);

  return <TradeMain></TradeMain>;
};

export default TradeMainPage;
