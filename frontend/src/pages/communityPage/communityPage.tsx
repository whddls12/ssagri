// @ts-ignore
import Navbar from '../../components/navbar';
// @ts-ignore
import { LoginPage } from '../../components/loginPage.styles';
import CommunityMain from '../../components/communityStyle/communityPage.styles';
import { CommuHeader } from '../../components/header';
import { isLoggedInAtom } from '../../states/account/loginAtom';
import { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';

const communityPage = () => {
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
      <CommuHeader></CommuHeader>
      <CommunityMain></CommunityMain>
    </div>
  );
};

export default communityPage;
