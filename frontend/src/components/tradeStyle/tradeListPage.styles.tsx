import { styled } from 'styled-components';
import {
  SearchDiv02,
  ProductList02,
  TradeProductItem02,
  TradeMainMap
} from './tradeMainPage.styles';
// @ts-ignore
import { useEffect, useRef, useState } from 'react';
// @ts-ignore
import axios from 'axios';
import { ProductItemType } from '../type';
import { useNavigate } from 'react-router-dom';

// Header 제외 중고거래 컴포넌트
const TradeListFrameDiv = styled.div`
  width: 1920px;
  height: 900px;
  margin: 50px auto 0;
  /* border: 2px solid black; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

const TradeListAllDiv = styled.div`
  width: 70%;
  height: 80%;
  /* border: 1px solid blue; */
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const TradeListLeftDiv = styled.div`
  width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const TradeListRightDiv = styled.div`
  width: 910px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

// TradeMainFrameDiv의 가운데 부분. 여기에 지도와 거래 main 내용이 들어감
const TradeListDiv = styled.div`
  width: 80%;
  /* height: 80%; */
  /* border: 1px solid blue; */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
`;

const CategorySpace = styled.div`
  width: 800px;
  height: 140px;
  margin-top: 0px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* border: 1px solid orange; */
`;

const CategoryName = styled.div`
  width: 300px;
  height: 60px;
  font-size: 36px;
  text-decoration: underline;
  text-align: center;
  line-height: 60px;
`;

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

const SearchSpace = styled.div`
  width: 800px;
  margin: 20px 0px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  /* border: 1px solid green; */
`;

//SearchSpace 안의 정렬선택창
const SearchOrder = styled.select`
  width: 100px;
  height: 30px;
  border: 1px solid #4786fa;
  border-radius: 5px;
  text-align: center;
  font-weight: bold;
`;

// 상품목록
const ProductList = styled.div`
  width: 840px;
  min-height: 520px;
  /* border: 1px solid red; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

// 페이징
const PagingSpace = styled.div`
  max-width: 80%;
  height: 50px;
  margin: 10px 0px;
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

const ProductListSearch = ({
  regionText,
  category,
  search,
  setSearch,
  order
}) => {
  // region 데이터를 받아왔으므로, axios 호출을 하여 List를 얻어온다.
  const [responseList, setResponseList] = useState([]);
  const [response, setResponse] = useState({});
  const [number, setNumber] = useState<number>(0);
  const userNo = localStorage.getItem('userNo');
  const accessToken = sessionStorage.getItem('accessToken');
  axios.defaults.headers.common['Authorization'] = `${accessToken}`;

  useEffect(() => {
    search = search === null ? '' : search;
    setSearch(search);
    let url = '';
    if (order === 'price') {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},asc&page=0&size=8`;
    } else {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},desc&page=0&size=8`;
    }
    axios
      .get(url)
      .then((res) => {
        setResponseList(res.data.content);
        // console.log('responseList: ', responseList);
        setResponse(res.data);
        // console.log('response', response);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    search = search === null ? '' : search;
    setSearch(search);
    let url = '';
    if (order === 'price') {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},asc&page=0&size=8`;
    } else {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},desc&page=0&size=8`;
    }
    axios
      .get(url)
      .then((res) => {
        setResponseList(res.data.content);
        // console.log('responseList: ', responseList);
        setResponse(res.data);
        // console.log('response', response);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [regionText, category, search]);

  useEffect(() => {
    let url = '';
    if (order === 'price') {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},asc&page=${number}&size=8`;
    } else {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},desc&page=${number}&size=8`;
    }
    axios
      .get(url)
      .then((res) => {
        setResponseList(res.data.content);
        // console.log('responseList: ', responseList);
        setResponse(res.data);
        // console.log('response', response);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [number]);

  useEffect(() => {
    let url = '';
    if (order === 'price') {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},asc&page=0&size=8`;
    } else {
      url = `usedproduct/${userNo}?region=${regionText}&category=${category}&search=${search}&sort=${order},desc&page=0&size=8`;
    }
    axios
      .get(url)
      .then((res) => {
        setResponseList(res.data.content);
        // console.log('responseList: ', responseList);
        setResponse(res.data);
        // console.log('response', response);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [order]);

  return (
    <div
      style={{
        width: '840px',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        alignItems: 'center'
      }}
    >
      <ProductList02 style={{ height: '520px' }}>
        {responseList.map((item: ProductItemType) => (
          <TradeProductItem02
            key={item.productNo}
            item={item}
          ></TradeProductItem02>
        ))}
      </ProductList02>
      <BottomPageSpace
        response={response}
        setNumber={setNumber}
      ></BottomPageSpace>
    </div>
  );
};

// region이 넘어왔을 때, region이 변경되었을 때 API를 호출시켜보자.
const TradeList = () => {
  // 먼저 region이 변경될 때마다 URI가 변경될 것이다.
  // 그러므로 URI에서 region 데이터를 가져온다.
  const [region, setRegion] = useState(
    new URLSearchParams(window.location.search).get('region')
  );
  // 검색어를 통해 검색했을 때의 데이터를 반환해보자.
  const [search, setSearch] = useState(
    new URLSearchParams(window.location.search).get('search')
  );
  // 이제 지역 뿐만 아니라, 카테고리나 검색어나 정렬 순서까지 들어왔을 때를 생각해보자
  const [category, setCategory] = useState('');
  const categoryList = ['', 'MONITER', 'KEYBOARD', 'MOUSE', 'LIFE', 'ETC'];
  const [order, setOrder] = useState('');

  // console.log('search: ', search);

  let regionText = '';
  // 요청을 보낼 때, 한글을 영어로 바꾸어야 BackEnd에서 인식한다.
  if (region === '서울' || region === 'SEOUL') {
    regionText = 'SEOUL';
  } else if (region === '대전' || region === 'DAEJEON') {
    regionText = 'DAEJEON';
  } else if (region === '구미' || region === 'GUMI') {
    regionText = 'GUMI';
  } else if (region === '광주' || region === 'GWANGJU') {
    regionText = 'GWANGJU';
  } else if (region === '부울경' || region === 'BUG') {
    regionText = 'BUG';
  }

  // console.log('QueryString region: ', regionText);

  // 지역 리스트를 선언하여, 맵에서 지역을 클릭할 때마다 region이 변경되도록 한다.
  const regionList = ['SEOUL', 'DAEJEON', 'GUMI', 'GWANGJU', 'BUG'];

  // 맵에서 지역을 클릭할 때마다 region이 변경되면,
  // 이를 인식하여 새로운 리스트를 가져와야 한다.
  // 가장 쉬운 방법은, 지금 List 페이지를 다시 호출하여 렌더링하는 것이다.
  // region에 의해서는 처음 한 번만 렌더링 할 것이므로, 두번째 인자를 []로 둔다.
  useEffect(() => {
    // console.log('regionText: ', regionText);
    // console.log('category: ', category);
    // console.log('search: ', search);

    let selectedCategory: any = document.querySelector('#allCategory');
    selectedCategory.style.color = '#4786fa';
    selectedCategory.style.fontWeight = 'bold';
    selectedCategory.style.textDecoration = 'underline';

    let tradeListInput: any = document.querySelector('#trade-list-input');
    tradeListInput.value = '';

    goTradeList(regionText, search);
  }, [region]);

  useEffect(() => {
    // console.log('regionText: ', regionText);
    // console.log('category: ', category);
    goTradeListWithCategory(regionText, category);
  }, [category]);

  useEffect(() => {
    // console.log('regionText: ', regionText);
    // console.log('category: ', category);
    // console.log('order: ', order);
    goTradeListWithCategory(regionText, category);
  }, [order]);

  const navigate = useNavigate();
  const goTradeList = (region: string, search: any) => {
    navigate(`/tradeList?region=${region}&search=${search}`);
  };

  const goTradeListWithCategory = (region: string, category: string) => {
    if (order !== 'price') {
      navigate(
        `/tradeList?region=${region}&category=${category}&search=${search}&sort=${order},desc&page=0&size=8`
      );
    } else {
      navigate(
        `/tradeList?region=${region}&category=${category}&search=${search}&sort=${order},asc&page=0&size=8`
      );
    }
  };

  return (
    <TradeListFrameDiv>
      <TradeListAllDiv>
        <TradeListLeftDiv>
          <TradeMainMap
            isList={true}
            setRegion={setRegion}
            setSearch={setSearch}
            setCategory={setCategory}
            regionList={regionList}
          ></TradeMainMap>
        </TradeListLeftDiv>
        <TradeListRightDiv>
          <TradeListDiv>
            <CategorySpace>
              <CategoryName>카테고리</CategoryName>
              <TradeListCategory
                setCategory={setCategory}
                categoryList={categoryList}
              ></TradeListCategory>
            </CategorySpace>
            <SearchSpace>
              <SearchDiv02
                regionText={regionText}
                category={category}
                search={search}
                setSearch={setSearch}
              ></SearchDiv02>
              <SearchOrder onChange={(e) => setOrder(e.target.value)}>
                <option value='like'>인기순</option>
                <option value='no'>최신순</option>
                <option value='price'>가격순</option>
              </SearchOrder>
            </SearchSpace>
            <ProductList>
              {/* 상품 목록이 보이는 컴포넌트 */}
              <ProductListSearch
                regionText={regionText}
                category={category}
                search={search}
                order={order}
                setSearch={setSearch}
              ></ProductListSearch>
            </ProductList>
          </TradeListDiv>
        </TradeListRightDiv>
      </TradeListAllDiv>
    </TradeListFrameDiv>
  );
};

const TradeListCategory = ({ setCategory, categoryList }) => {
  const changeCategory = (num: number) => {
    setCategory(categoryList[num]);
    let selectedCategory: any;
    if (num == 0) {
      selectedCategory = document.querySelector('#allCategory');
    } else if (num == 1) {
      selectedCategory = document.querySelector('#moniter');
    } else if (num == 2) {
      selectedCategory = document.querySelector('#keyboard');
    } else if (num == 3) {
      selectedCategory = document.querySelector('#mouse');
    } else if (num == 4) {
      selectedCategory = document.querySelector('#life');
    } else if (num == 5) {
      selectedCategory = document.querySelector('#etc');
    }
    let allCategory = document.querySelectorAll('.category');
    allCategory.forEach((category: any) => {
      category.style.color = '#000';
      category.style.fontWeight = 'normal';
      category.style.textDecoration = 'none';
    });
    selectedCategory.style.color = '#4786fa';
    selectedCategory.style.fontWeight = 'bold';
    selectedCategory.style.textDecoration = 'underline';
  };
  return (
    <CategoryList>
      <CategoryItem
        id='allCategory'
        className='category'
        onClick={() => changeCategory(0)}
      >
        전체
      </CategoryItem>
      <CategoryItem
        id='moniter'
        className='category'
        onClick={() => changeCategory(1)}
      >
        모니터
      </CategoryItem>
      <CategoryItem
        id='keyboard'
        className='category'
        onClick={() => changeCategory(2)}
      >
        키보드
      </CategoryItem>
      <CategoryItem
        id='mouse'
        className='category'
        onClick={() => changeCategory(3)}
      >
        마우스
      </CategoryItem>
      <CategoryItem
        id='life'
        className='category'
        onClick={() => changeCategory(4)}
      >
        생활용품
      </CategoryItem>
      <CategoryItem
        id='etc'
        className='category'
        onClick={() => changeCategory(5)}
      >
        기타용품
      </CategoryItem>
    </CategoryList>
  );
};
const AuctionTradeList = () => {
  return (
    <CategoryList>
      <CategoryItem>전체</CategoryItem>
      <CategoryItem>모니터</CategoryItem>
      <CategoryItem>키보드</CategoryItem>
      <CategoryItem>마우스</CategoryItem>
      <CategoryItem>생활용품</CategoryItem>
      <CategoryItem>기타용품</CategoryItem>
    </CategoryList>
  );
};

const BottomPageSpace = ({ response, setNumber }) => {
  // console.log('BottomPageSpace', response);
  const totalPages: number = response.totalPages;
  const currentPage = response.number + 1;

  let pageNumbers: any = document.querySelectorAll('.pageNumbers');
  if (pageNumbers) {
    pageNumbers.forEach((element: any) => {
      element.style.color = '#000';
      element.style.backgroundColor = '#fff';
      element.style.boxShadow = 'none';
    });
  }

  let currentNumber: any = document.querySelector('#pageNumber' + currentPage);
  if (currentNumber) {
    currentNumber.style.color = '#fff';
    currentNumber.style.backgroundColor = '#4786fa';
    currentNumber.style.boxShadow = '1px 1px 2px 2px #929292';
  }

  const changePage = (num: number) => {
    // console.log('changePage num: ', num);
    setNumber(num - 1);
  };

  const changeOneMinus = () => {
    setNumber(currentPage - 2);
  };

  const changeOnePlus = () => {
    setNumber(currentPage);
  };

  const changeFirstPage = () => {
    setNumber(0);
  };

  const changeLastPage = () => {
    setNumber(totalPages - 1);
  };

  const rendering = () => {
    const result: any = [];
    if (response.first) {
      result.push(
        <PagingButton
          key={-2}
          style={{
            color: 'rgb(0,0,0,0.3)',
            borderColor: 'rgb(0,0,0,0.3)'
          }}
        >
          <PagingButtonText>&lt;&lt;</PagingButtonText>
        </PagingButton>
      );
      result.push(
        <PagingButton
          key={-1}
          style={{
            color: 'rgb(0,0,0,0.3)',
            borderColor: 'rgb(0,0,0,0.3)'
          }}
        >
          <PagingButtonText>&lt;</PagingButtonText>
        </PagingButton>
      );
    } else {
      result.push(
        <PagingButton key={-2} onClick={changeFirstPage}>
          <PagingButtonText>&lt;&lt;</PagingButtonText>
        </PagingButton>
      );
      result.push(
        <PagingButton key={-1} onClick={changeOneMinus}>
          <PagingButtonText>&lt;</PagingButtonText>
        </PagingButton>
      );
    }

    for (let i = 1; i <= totalPages; i++) {
      result.push(
        <PagingButton
          key={i}
          id={'pageNumber' + i}
          className='pageNumbers'
          onClick={() => changePage(i)}
        >
          <PagingButtonText>{i}</PagingButtonText>
        </PagingButton>
      );
    }

    if (response.last) {
      result.push(
        <PagingButton
          key={11}
          style={{
            color: 'rgb(0,0,0,0.3)',
            borderColor: 'rgb(0,0,0,0.3)'
          }}
        >
          <PagingButtonText>&gt;</PagingButtonText>
        </PagingButton>
      );
      result.push(
        <PagingButton
          key={12}
          style={{
            color: 'rgb(0,0,0,0.3)',
            borderColor: 'rgb(0,0,0,0.3)'
          }}
        >
          <PagingButtonText>&gt;&gt;</PagingButtonText>
        </PagingButton>
      );
    } else {
      result.push(
        <PagingButton key={11} onClick={changeOnePlus}>
          <PagingButtonText>&gt;</PagingButtonText>
        </PagingButton>
      );
      result.push(
        <PagingButton key={12} onClick={changeLastPage}>
          <PagingButtonText>&gt;&gt;</PagingButtonText>
        </PagingButton>
      );
    }
    return result;
  };

  return <PagingSpace>{rendering()}</PagingSpace>;
};
export { TradeList, AuctionTradeList, BottomPageSpace };
