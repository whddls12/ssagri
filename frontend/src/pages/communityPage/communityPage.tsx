// @ts-ignore
import Navbar from '../../components/navbar';
// @ts-ignore
import { LoginPage } from '../../components/loginPage.styles';
import CommunityMain from '../../components/communityStyle/communityPage.styles';
import { CommuHeader } from '../../components/header';
const communityPage = () => {
  return (
    <div>
      <CommuHeader></CommuHeader>
      <CommunityMain></CommunityMain>
    </div>
  );
};

export default communityPage;
