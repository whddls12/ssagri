import { styled } from 'styled-components';

const AuctionDiv = styled.div`
  width: 100%;
`;

const AuctionBody = styled.div`
  width: 100%;
  height: 1000px;
`;

const AuctionPage = () => {
  return (
    <AuctionDiv>
      <AuctionBody></AuctionBody>
    </AuctionDiv>
  );
};

export { AuctionPage };
