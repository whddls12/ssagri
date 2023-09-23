import { styled } from 'styled-components';

const DetailDiv = styled.div`
  width: 100%;
  height: 1000px;

  /* border: 3px solid black; */
`;
const TopTitle = styled.div`
  width: 100%;
  height: 200px;
  background-color: #f2ead3;
`;

const BoardDetailMain = () => {
  return (
    <DetailDiv>
      <TopTitle>질문게시판</TopTitle>
      디테일 페이지
    </DetailDiv>
  );
};

export { BoardDetailMain };
