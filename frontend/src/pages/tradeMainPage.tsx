import Navbar from '../components/navbar';
import {
  TradeMainPageDiv,
  TradeMain
} from '../components/tradeMainPage.styles';

const TradeMainPage = () => {
  //   const navigate = useNavigate();
  //   const goMain = () => {
  //     navigate('/main');
  //   };
  return (
    <TradeMainPageDiv>
      <Navbar></Navbar>
      <TradeMain></TradeMain>
    </TradeMainPageDiv>
  );
};

export default TradeMainPage;
