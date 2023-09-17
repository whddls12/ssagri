import { styled } from 'styled-components';

const DetailFrame = styled.div`
  width: 1920px;
  height: 1080px;
  margin-top: 7vh;
  /* border: 2px solid black; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

const TradeDetail = () => {
  return <DetailFrame>상품 디테일</DetailFrame>;
};

export { TradeDetail };
