import { useNavigate } from 'react-router-dom';

const loginPage = () => {
  const navigate = useNavigate();

  const goMain = () => {
    navigate('/main');
  };

  return (
    <div className='App'>
      <header className='App-header'>
        <p>로그인페이지</p>
        <div onClick={goMain}>메인으로</div>
      </header>
    </div>
  );
};

export default loginPage;
