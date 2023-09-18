import { styled } from 'styled-components';

const ChattingFrame = styled.div`
  width: 1920px;
  height: 1080px;
  margin-top: 7vh;
  /* border: 2px solid black; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Chatting = () => {
  return <ChattingFrame>채팅 페이지</ChattingFrame>;
};

export { Chatting };
