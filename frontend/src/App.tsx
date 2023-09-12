import './App.css';
import { Route, Routes } from 'react-router-dom';

// 각 페이지 정보
import LoginPage from './pages/loginPage';
import MainPage from './pages/mainPage';

const App = () => {
  return (
    <Routes>
      {/* 로그인 페이지 */}
      <Route path='/' element={<LoginPage />} />
      {/* 메인 페이지 */}
      <Route path='/main' element={<MainPage />} />
    </Routes>
  );
};

export default App;
