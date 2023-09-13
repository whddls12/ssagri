// import { useNavigate } from 'react-router-dom';
import Navbar from '../components/navbar';
import TradeMainPage from '../components/tradeMainPage.styles';
import TradeMain from '../components/tradeMain';

const tradeMainPage = () => {
  //   const navigate = useNavigate();

  //   const goMain = () => {
  //     navigate('/main');
  //   };

  return (
    <TradeMainPage>
      <Navbar></Navbar>
      <TradeMain></TradeMain>
    </TradeMainPage>
  );
};

export default tradeMainPage;
