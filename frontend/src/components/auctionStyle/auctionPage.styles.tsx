import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { isLoggedInAtom } from '../../states/account/loginAtom';

const AuctionDiv = styled.div`
  width: 100%;
`;

const AuctionBody = styled.div`
  width: 80%;
  height: 1000px;
  margin: 0 auto;
`;

const Line = styled.hr`
  width: 400px;
  margin: 0 auto;
  animation: fadein 1.5s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const Line2 = styled.hr`
  width: 100%;
  margin: 0 auto;
  animation: fadein 3.5s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const AuctionTag1 = styled.div`
  text-align: center;
  font-size: 22px;
  margin: 30px 0 30px 0px;
  animation: fadein 1.5s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const AuctionTag2 = styled.div`
  font-size: 27px;
  font-weight: 600;
  margin-top: 40px;
  margin-left: 200px;
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
const AuctionTag3 = styled.div`
  font-size: 20px;
  animation: fadein 3s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const AuctionTag4 = styled.div`
  display: flex;
  justify-content: center;
`;
// const AuctionTag5 = styled.div`
//   font-size: 20px;
//   position: absolute;
//   left: 1120px;
//   bottom: 0;
// `;
const AuctionTag6 = styled.div`
  animation: fadein 4s ease-in-out;

  @keyframes fadein {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;
const AuctionInputDiv = styled.div`
  margin-top: 25px;
  margin-bottom: 20px;
  position: relative;
  display: flex;
  justify-content: center;
`;

const AuctionCreateBtn = styled.div`
  width: 100px;
  height: 50px;
  background-color: #555453;
  margin-left: 1350px;
  border-radius: 10px;
  text-align: center;
  line-height: 50px;
  color: white;
`;
const AuctionList = styled.div`
  width: 1200px;
  height: 700px;
  /* border: 2px solid black; */
  margin: 70px auto;
  border-radius: 4px;
`;
const AuctionItem = styled.div`
  position: relative;
  width: 1050px;
  height: 150px;
  border: 1px solid rgb(0, 0, 0, 0.3);
  box-shadow: 3px 3px rgb(0, 0, 0, 0.3);
  border-radius: 10px;
  margin: 70px auto;
  display: flex;

  &.종료 {
    opacity: 0.4;
  }
`;

const ItemImg = styled.img`
  width: 120px;
  height: 120px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
  margin: 14px 60px 0 60px;
`;
const ItemTitle = styled.div`
  font-size: 30px;
  margin-top: 18px;
`;

const ItmeDiv1 = styled.div``;
const ItmeDiv2 = styled.div``;
const ItemTag1 = styled.div`
  font-size: 15px;
  margin-top: 29px;
`;
const ItemTag2 = styled.div`
  font-size: 15px;
  margin-top: 10px;
  color: #ff5151;
`;
const ItemTime1 = styled.div`
  position: absolute;
  top: 30px;
  left: 700px;
  font-size: 15px;
  color: #ff5151;
`;
const ItemCurrent1 = styled.div`
  position: absolute;
  top: 22px;
  left: 960px;
  width: 60px;
  height: 30px;
  color: white;
  border-radius: 7px;
  text-align: center;
  line-height: 30px;

  &.진행중 {
    background-color: red;
  }

  &.예정 {
    background-color: #4786fa;
  }

  &.종료 {
    background-color: #929292;
  }
`;
// const ItemCurrent2 = styled.div`
//   position: absolute;
//   top: 110px;
//   left: 960px;
//   width: 100px;
// `;

const PagingSpace = styled.div`
  width: 50%;
  height: 50px;
  margin: 50px 0px;
  /* border: 1px solid red; */
  display: flex;
  justify-content: center;
  align-items: center;
`;
const PagingButton = styled.button`
  width: 40px;
  height: 40px;
  margin: 0 2px;
  border: 1px solid #4786fa;
  border-radius: 5px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  &:hover {
    /* border: 2px solid #4786fa;
    background-color: #4786fa;
    color: #fff; */
    box-shadow: 1px 1px 2px 2px #757575;
  }
`;
const PagingButtonText = styled.div`
  height: 30px;
  font-size: 20px;
  font-weight: bold;
`;

///// 경매 키워드 검색
// ------ 검색 입력 01 --------
const Search01 = styled.div`
  width: 500px;
  height: 40px;
  border: 1px solid #4786fa;
  border-radius: 20px;
  padding: 0px 30px 0px 0px;
  margin-right: 25px;

  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const SearchInput01 = styled.input`
  width: 440px;
  height: 38px;
  border: 0;
  margin-left: 16px;
  font-size: 16px;
`;

// ------ 검색 버튼 --------
const SearchButton = styled.button`
  background-color: #fff;
  border: 0;
  cursor: pointer;
`;
const SearchImg = styled.img`
  width: 20px;
  height: 20px;
  color: #4786fa;
`;

const AuctionSearchInput = ({
  onKeywordHandler,
  getListByKeyword,
  handleKeyDown
}) => {
  return (
    <Search01>
      <SearchInput01
        onChange={onKeywordHandler}
        onKeyDown={handleKeyDown}
        type='text'
        placeholder='원하는 제품을 검색해 보세요!'
      ></SearchInput01>
      <SearchButton onClick={getListByKeyword}>
        <SearchImg src='/assets/img/searchGlass-4786fa.png'></SearchImg>
      </SearchButton>
    </Search01>
  );
};

////

const BottomPageSpace = ({ setCurrentPage, totalPage }) => {
  const NextPage = (num) => {
    setCurrentPage(num);
    // console.log(num);
  };
  const pageButtons: JSX.Element[] = [];

  for (let pageNumber = 1; pageNumber <= totalPage; pageNumber++) {
    pageButtons.push(
      <PagingButton key={pageNumber} onClick={() => NextPage(pageNumber)}>
        <PagingButtonText>{pageNumber}</PagingButtonText>
      </PagingButton>
    );
  }

  return (
    <PagingSpace>
      <PagingButton>
        <PagingButtonText>&lt;&lt;</PagingButtonText>
      </PagingButton>
      <PagingButton>
        <PagingButtonText>&lt;</PagingButtonText>
      </PagingButton>
      {pageButtons}
      <PagingButton>
        <PagingButtonText>&gt;</PagingButtonText>
      </PagingButton>
      <PagingButton>
        <PagingButtonText>&gt;&gt;</PagingButtonText>
      </PagingButton>
    </PagingSpace>
  );
};

const AuctionItme = (item: any) => {
  const navigate = useNavigate();
  // 상세 페이지로 이동
  const goAuctionDetail = (item) => {
    // console.log(item);
    navigate(`/auctionDetail/${item.item.no}`);
  };
  const storedAccessToken = sessionStorage.getItem('accessToken');
  const AuctionApi = axios.create({
    headers: { Authorization: storedAccessToken }
  });
  const [thumbnail, setThumbnail] = useState('');

  const getThumbnail = () => {
    AuctionApi.get(`/auction-product/load/${item.item.no}`)
      .then((res) => {
        if (res.data.resultMsg === '등록된 사진이 있습니다.') {
          const firstPhoto = res.data.result[0].imageLink;
          setThumbnail(firstPhoto);
        } else {
          setThumbnail('/assets/img/사진이 없어요.jfif');
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  ////

  const [ddayHour, setddayHour] = useState('00');
  const [ddayMin, setddayMin] = useState('00');
  const [ddaySec, setddaySec] = useState('00');

  // 남은 시간 카운트 다운
  const remaindTime = () => {
    const koreanDate = item.item.endDate;

    const parts = koreanDate.match(/\d+/g);

    if (parts !== null && parts.length >= 0) {
      const year = parseInt(parts[0], 10); // 연도
      const month = parseInt(parts[1], 10); // 월
      const day = parseInt(parts[2], 10); // 일
      const hour = parseInt(parts[3], 10); // 시
      const minute = parseInt(parts[4], 10); // 분
      const second = parseInt(parts[5], 10); // 초

      const now: Date = new Date();
      const end: Date = new Date(year, month - 1, day, hour, minute, second);

      let nt: number = now.getTime();
      let et: number = end.getTime();

      if (nt < et) {
        let sec: string | number = Math.floor((et - nt) / 1000); // 초 단위로 남은 시간 계산
        let hour: string | number = Math.floor(sec / 3600); // 시간 계산
        sec %= 3600;
        let min: string | number = Math.floor(sec / 60); // 분 계산
        sec %= 60;

        // 시, 분, 초가 10보다 작을 때 앞에 0을 추가
        hour = hour < 10 ? '0' + hour : hour;
        min = min < 10 ? '0' + min : min;
        sec = sec < 10 ? '0' + sec : sec;

        // HTML 요소에 시간 정보 업데이트
        // document.getElementById('d-day-hour')!.innerHTML = hour.toString();
        // document.getElementById('d-day-min')!.innerHTML = min.toString();
        // document.getElementById('d-day-sec')!.innerHTML = sec.toString();
        setddayHour(hour.toString());
        setddayMin(min.toString());
        setddaySec(sec.toString());
      } else {
        // 현재시간이 종료시간보다 크면
        setddayHour('00');
        setddayMin('00');
        setddaySec('00');
      }
    }
  };

  useEffect(() => {
    const intervalid = setInterval(remaindTime, 1000);

    return () => {
      clearInterval(intervalid);
    };
  }, [item.item.endDate]);

  ////

  useEffect(() => {
    getThumbnail();
  });

  return (
    <AuctionItem
      className={item.item.auctionStatus}
      onClick={() => {
        goAuctionDetail(item);
      }}
    >
      <ItemImg src={thumbnail}></ItemImg>

      <ItmeDiv1>
        <ItemTitle>{item.item.name}</ItemTitle>
        <ItemTag1>시작가 {item.item.downPrice}</ItemTag1>
        <ItemTag2>현재가 {item.item.finallyPrice} </ItemTag2>
      </ItmeDiv1>
      <ItemTime1>
        {item.item.auctionStatus === '진행중' ? (
          <div>
            <div style={{ color: 'black' }}>남은 시간</div>
            <div className='time'>
              <span id='d-day-hour'>{ddayHour}</span>
              <span className='col'>:</span>
              <span id='d-day-min'>{ddayMin}</span>
              <span className='col'>:</span>
              <span id='d-day-sec'>{ddaySec}</span>
            </div>
          </div>
        ) : (
          <div>
            <div style={{ color: 'black' }}>남은 시간</div>
            <div className='time' style={{ color: 'black' }}>
              <span id='d-day-hour'>00</span>
              <span className='col'>:</span>
              <span id='d-day-min'>00</span>
              <span className='col'>:</span>
              <span id='d-day-sec'>00</span>
            </div>
          </div>
        )}
      </ItemTime1>
      <ItmeDiv2>
        <ItemCurrent1 className={item.item.auctionStatus}>
          {item.item.auctionStatus}
        </ItemCurrent1>
        {/* <ItemCurrent2>참여자수</ItemCurrent2> */}
      </ItmeDiv2>
    </AuctionItem>
  );
};

const CategoryList = styled.div`
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const CategoryItem = styled.div`
  width: 130px;
  height: 50px;
  font-size: 20px;
  text-align: center;
  line-height: 50px;
  &:hover {
    color: #4786fa;
    font-weight: bold;
    text-decoration: underline;
  }
`;

const AuctionTradeList = ({ setTypes }) => {
  const CheckType = (num) => {
    setTypes(num);
  };

  return (
    <CategoryList>
      <CategoryItem onClick={() => CheckType(0)}>전체</CategoryItem>
      <CategoryItem onClick={() => CheckType(1)}>모니터</CategoryItem>
      <CategoryItem onClick={() => CheckType(2)}>키보드</CategoryItem>
      <CategoryItem onClick={() => CheckType(3)}>마우스</CategoryItem>
      <CategoryItem onClick={() => CheckType(4)}>생활용품</CategoryItem>
      <CategoryItem onClick={() => CheckType(5)}>기타용품</CategoryItem>
    </CategoryList>
  );
};

const AuctionPage = () => {
  const [itemList, setItemList] = useState([]);

  const [types, setTypes] = useState(0);

  const [keyword, SetKeyword] = useState('');

  const onKeywordHandler = (e) => {
    SetKeyword(e.target.value);
  };

  const handleKeyDown = (event) => {
    const key = event.code;
    switch (key) {
      case 'Enter':
        getListByKeyword();
        break;
      default:
    }
  };

  const getListByKeyword = () => {
    const auctionSearch = keyword;

    axios
      .get(`/auction-product/search`, {
        params: {
          auctionSearch: auctionSearch
        }
      })
      .then((res) => {
        setItemList(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 로그인하고 다시 돌아오기 위해 현재 경로 저장
  // const { pathname } = useLocation();
  // console.log(pathname);

  // const [researchItems,setResearchItems] =
  // const researchItems = itemList.filter(item => item.type === '모니터');

  // useEffect(()=>{
  //   const filteredItems = itemList.filter(item =>{
  //     if (item.item.type ===)
  //   })
  // },[types])

  // 페이지 계산 로직
  const [currentPage, setCurrentPage] = useState(1);
  const startIndex = (currentPage - 1) * 4;
  const endIndex = startIndex + 4;
  const SortList = itemList.slice(startIndex, endIndex);

  const totalPage = Math.ceil(itemList.length / 4);

  const navigate = useNavigate();
  const goAuctionCreate = () => {
    navigate('/auctionCreate');
  };

  // 경매 리스트 요청
  const storedAccessToken = sessionStorage.getItem('accessToken');
  const auctionApi = axios.create({
    headers: { Authorization: storedAccessToken }
  });

  const GetAuctionItemList = () => {
    // console.log(isLoggedIn);
    auctionApi
      .get('/auction-product/all-list')
      .then((res) => {
        // console.log(res.data, '성공2');
        setItemList(res.data);
        // itemList.current = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const isLoggedIn = useRecoilValue(isLoggedInAtom);

  useEffect(() => {
    if (isLoggedIn) {
      GetAuctionItemList();
    } else {
    }
  }, [isLoggedIn]);

  const formatType = (type) => {
    if (type === 1) {
      return '모니터';
    } else if (type === 2) {
      return '키보드';
    } else if (type === 3) {
      return '마우스';
    } else if (type === 4) {
      return '생활용품';
    } else if (type === 5) {
      return '기타용품';
    } else if (type === 0) {
      return '전체';
    }
  };

  // 카테고리별 경매 조회
  useEffect(() => {
    const getListByType = () => {
      const auctionType = formatType(types);
      if (types === 0) {
        GetAuctionItemList();
      } else {
        axios
          .get(`/auction-product/type`, {
            params: {
              auctionType: auctionType
            }
          })
          .then((res) => {
            setItemList(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    };
    getListByType();
  }, [types]);

  return (
    <AuctionDiv>
      <AuctionBody>
        <AuctionTag1>검색결과 {itemList.length}건</AuctionTag1>

        <Line></Line>
        <AuctionTag2></AuctionTag2>
        <AuctionTag3>
          <AuctionInputDiv>
            <AuctionSearchInput
              onKeywordHandler={onKeywordHandler}
              getListByKeyword={getListByKeyword}
              handleKeyDown={handleKeyDown}
            ></AuctionSearchInput>
          </AuctionInputDiv>
        </AuctionTag3>
        <Line2></Line2>
        <AuctionTag6>
          <AuctionTradeList setTypes={setTypes}></AuctionTradeList>
          <AuctionList>
            {SortList ? (
              SortList.map((item, id) => (
                <AuctionItme key={id} item={item}></AuctionItme>
              ))
            ) : (
              <div>등록된 경매가 없습니다.</div>
            )}
          </AuctionList>
          <AuctionCreateBtn onClick={goAuctionCreate}>
            경매 등록
          </AuctionCreateBtn>
          <AuctionTag4>
            <BottomPageSpace
              setCurrentPage={setCurrentPage}
              totalPage={totalPage}
            ></BottomPageSpace>
          </AuctionTag4>
        </AuctionTag6>
      </AuctionBody>
    </AuctionDiv>
  );
};

export { AuctionPage };
