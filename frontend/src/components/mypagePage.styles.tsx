import { styled } from 'styled-components';
// @ts-ignore
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const MyPageFrame = styled.div`
  width: 1920px;
  height: 890px;
  margin-top: 7vh;
  /* background-color: rgb(0, 0, 0, 0.3); */
  /* border: 2px solid black; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

const MyPage = () => {
  // @ts-ignore
  const user = localStorage.getItem('userNo');
  const accessToken = sessionStorage.getItem('accessToken');
  axios.defaults.headers.common['Authorization'] = `${accessToken}`;

  return (
    <>
      <MyPageFrame></MyPageFrame>
    </>
  );
};

export { MyPage };
