import Navbar from '../components/navbar';
import {
  TradeListPageDiv,
  TradeList
} from '../components/tradeListPage.styles';

const TradeMainPage = () => {
  //   const navigate = useNavigate();
  //   const goMain = () => {
  //     navigate('/main');
  //   };
  return (
    <TradeListPageDiv>
      <Navbar></Navbar>
      <TradeList></TradeList>
    </TradeListPageDiv>
  );
};

export default TradeMainPage;
