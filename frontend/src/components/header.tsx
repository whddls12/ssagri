import { styled } from 'styled-components';

const Headers = styled.div`
  width: 100%;
  height: 250px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: whitesmoke;
  position: relative;
  overflow: hidden;
`;
const AuctionTitle = styled.div`
  margin-top: 100px;
  /* margin-left: 50px; */
  width: 300px;
  height: 100px;
  text-align: center;
  color: black;
  font-size: 35px;
  font-weight: 600;
  animation: fadein 0.5s ease-in-out;
  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const AuctionTitlecolor = styled.span`
  color: #d8710b;
`;
const CommuTitlecolor = styled.span`
  color: #1eb0ca;
`;
const CommuTitle = styled.div`
  text-align: center;
  margin-top: 75px;

  width: 300px;
  height: 100px;
  color: black;
  font-size: 35px;
  font-weight: 600;
  animation: fadein 0.5s ease-in-out;
  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const AuctionLeftImg = styled.img`
  width: 250px;
  position: absolute;
  left: 300px;
  bottom: 0px;
  animation: slideIn1 1s ease-in-out;
  @keyframes slideIn1 {
    0% {
      transform: translateY(50%);
      opacity: 0;
    }
    100% {
      transform: translateY(0);
      opacity: 0.9;
    }
  }
`;
const AuctionRightImg = styled.img`
  width: 150px;
  position: absolute;
  left: 1420px;
  bottom: 80px;
  animation: slideIn2 1s ease-in-out;
  @keyframes slideIn2 {
    0% {
      transform: translateY(-50%);
      opacity: 0;
    }
    100% {
      transform: translateY(0);
      opacity: 0.9;
    }
  }
`;
const CommuLeftImg = styled.img`
  width: 160px;
  position: absolute;
  left: 330px;
  bottom: -10px;
  animation: slideIn3 1s ease-in-out;
  @keyframes slideIn3 {
    0% {
      transform: translateY(50%);
      opacity: 0;
    }
    100% {
      transform: translateY(0);
      opacity: 0.9;
    }
  }
`;
const CommuRightImg = styled.img`
  width: 120px;
  position: absolute;
  left: 1380px;
  bottom: 70px;
  animation: slideIn4 1s ease-in-out;
  @keyframes slideIn4 {
    0% {
      transform: translateY(-50%);
      opacity: 0;
    }
    100% {
      transform: translateY(0);
      opacity: 0.9;
    }
  }
`;

const CommuTitleTag = styled.div`
  margin-top: 15px;
  font-size: 25px;
  animation: fadein 2.5s ease-in-out;
  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const AuctionHeader = () => {
  return (
    <Headers>
      <AuctionLeftImg src='assets/img/auction1.png'></AuctionLeftImg>
      <AuctionTitle>
        <AuctionTitlecolor>경매</AuctionTitlecolor> 종합 검색
      </AuctionTitle>
      <AuctionRightImg src='assets/img/auction2.png'></AuctionRightImg>
    </Headers>
  );
};
const CommuHeader = () => {
  return (
    <Headers>
      <CommuLeftImg src='assets/img/commu1.png'></CommuLeftImg>
      <CommuTitle>
        <CommuTitlecolor>커뮤니티</CommuTitlecolor> 게시판
        <CommuTitleTag>공통의 관심사를 소통해보세요.</CommuTitleTag>
      </CommuTitle>
      <CommuRightImg src='assets/img/commu2.png'></CommuRightImg>
    </Headers>
  );
};

export { AuctionHeader, CommuHeader };
