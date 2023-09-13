import { useNavigate } from 'react-router-dom';
import Navbar from '../components/navbar';
import LoginPage from '../components/loginPage.styles';

const loginPage = () => {
  const navigate = useNavigate();

  const goMain = () => {
    navigate('/main');
  };

  return (
    <LoginPage>
      <Navbar></Navbar>
      <p>로그인페이지</p>
      <div onClick={goMain}>메인으로</div>
    </LoginPage>
  );
};

export default loginPage;
