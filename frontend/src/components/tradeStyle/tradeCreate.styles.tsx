import { styled } from 'styled-components';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '../../ckeditor';
// import { ClassicEditor } from '@ckeditor/ckeditor5-editor-classic';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const CreateAndUpdateFrame = styled.div`
  width: 1920px;
  min-height: 1080px;
  margin-top: 7vh;
  /* border: 2px solid black; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

const CreateAndUpdateDiv = styled.div`
  width: 1400px;
  height: 980px;
  /* border: 1px solid blue; */
  display: flex;
  flex-direction: column;
`;

const CreateAndUpdateDivHeader = styled.div`
  width: 100%;
  height: 60px;
  /* border: 1px solid red; */
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const CreateAndUpdateDivHeaderItem = styled.div`
  /* width: 100%;
  height: 50px; */
  /* border: 1px solid red; */
  /* margin-left: 20px; */
  line-height: 40px;
  font-size: 32px;
  font-weight: bold;
  border-bottom: 2px solid black;
`;

const CreateAndUpdateButton = styled.button`
  width: 140px;
  height: 40px;
  margin-right: 2px;
  border: 0;
  border-radius: 5px;
  background-color: #4786fa;
  box-shadow: 2px 2px 2px 1px #757575;
  color: #fff;
  font-size: 20px;
  font-weight: bold;
  &:hover {
    border: 2px solid #4786fa;
    background-color: #fff;
    color: #4786fa;
  }
`;

const BackButton = styled(CreateAndUpdateButton)`
  background-color: tomato;
  margin-right: 10px;
  &:hover {
    border: 2px solid tomato;
    color: tomato;
  }
`;

const CreateAndUpdateDivItem = styled.div`
  width: 100%;
  height: 900px; /*60px 남음*/
  margin-top: 20px;
  /* border: 3px solid cyan; */
  display: flex;
  flex-direction: column;
`;

const CreateAndUpdateDivItemUp = styled.div`
  width: 100%;
  /* height: 130px; */
  display: flex;
`;

const CreateAndUpdateDivItemUpLeft = styled.div`
  width: 100%;
  /* border: 1px solid blue; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const LeftItemName = styled.input`
  width: 100%;
  height: 40px;
  border: 1px solid #4786fa;
`;

const LeftItemCategory = styled.select`
  width: 100%;
  height: 40px;
  border: 1px solid #4786fa;
`;

const LeftItemPrice = styled(LeftItemName)``;

// const CreateAndUpdateDivItemUpRight = styled.div`
//   width: 0%;
//   /* border: 1px solid red; */
//   display: flex;
//   justify-content: center;
//   align-items: center;
// `;

const RightItemImage = styled.div`
  width: 100%;
  height: 30px;
  border: 1px solid #4786fa;
  overflow-y: hidden;
  overflow-x: auto;
`;

const CreateAndUpdateDivItemDown = styled.div`
  width: 100%;
  border: 1px solid #4786fa;
  display: flex;
  justify-content: center;
  align-items: center;
`;

interface Product {
  userNo: string | null;
  productCategory: string;
  title: string;
  content: string;
  price: number;
  saleStatus: string;
}

const TradeCreate = () => {
  const user = localStorage.getItem('userNo');
  const FILE_SIZE_MAX_LIMIT = 100 * 1024 * 1024;
  const [thumbnailPhoto, setthumbnailPhoto] = useState<File | null>(null);
  const accessToken = sessionStorage.getItem('accessToken');
  axios.defaults.headers.common['Authorization'] = `${accessToken}`;
  // const [data, setData] = useState<string>('');
  //axios 전달을 위한 데이터 key값을 먼저 지정해둠
  // @ts-ignore
  const [product, setProduct] = useState<Product>({
    userNo: user,
    productCategory: '',
    title: '',
    content: '',
    price: 0,
    saleStatus: 'READY'
  });
  // 이후 product input에 작성한 내용을 다시 전달함
  // @ts-ignore
  const { userNo, productCategory, title, content, price, saleStatus } =
    product;

  const navigate = useNavigate();
  // 뒤로가기 버튼
  const backToMain = () => {
    navigate('/tradeMain');
  };

  // --- 함수 영역 ---

  const savethumbnailPhoto = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files !== null) {
      if (event.target.files[0].size > FILE_SIZE_MAX_LIMIT) {
        event.target.value = '';
        alert('업로드 가능한 최대 용량은 100MB입니다. ');
        return;
      } else {
        const thumbnailfile = event.target.files[0];
        // console.log(thumbnailfile);
        if (thumbnailfile) {
          setthumbnailPhoto(thumbnailfile);
        } else {
          setthumbnailPhoto(null);
        }
      }
    }
  };

  const saveProduct = async (e: any) => {
    e.preventDefault();
    // if (!title || !productCategory || !price) {
    //   alert('모든 칸을 입력해주세요.');
    // } else {
    const data = new FormData();
    const productInfo = JSON.stringify(product);
    const productblob = new Blob([productInfo], {
      type: 'application/json'
    });
    data.append('usedProductSaveRequest', productblob);
    data.append('s3uploadMain', thumbnailPhoto as File);
    // console.log(data);
    try {
      await axios.post('/usedproduct', data, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Credentials': 'true'
        }
      });
      // console.log('요청성공');
      alert('등록되었습니다.');
      navigate('/tradeMain');
    } catch (error) {
      console.log(error);
    }
    // }
  };

  const onChangeTitle = (e) => {
    product.title = e.target.value;
    // console.log(product);
  };
  const onChangeCategory = (e) => {
    product.productCategory = e.target.value;
    // console.log(product);
  };
  const onChangePrice = (e) => {
    product.price = e.target.value;
    // console.log(product);
  };
  const onChangeContent = (inputData) => {
    product.content = inputData;
    // console.log(product);
  };

  return (
    <CreateAndUpdateFrame>
      <CreateAndUpdateDiv>
        <CreateAndUpdateDivHeader>
          <CreateAndUpdateDivHeaderItem>물품등록</CreateAndUpdateDivHeaderItem>
          <div>
            <BackButton onClick={backToMain}>뒤로가기</BackButton>
            <CreateAndUpdateButton onClick={saveProduct}>
              등록
            </CreateAndUpdateButton>
          </div>
        </CreateAndUpdateDivHeader>
        <CreateAndUpdateDivItem>
          <CreateAndUpdateDivItemUp>
            <CreateAndUpdateDivItemUpLeft>
              <LeftItemName
                type='text'
                placeholder='상품명'
                name='title'
                onChange={onChangeTitle}
              ></LeftItemName>
              <LeftItemCategory
                name='productCategory'
                id='level'
                onChange={onChangeCategory}
              >
                <option value=''>선택</option>
                <option value='KEYBOARD'>키보드</option>
                <option value='MOUSE'>마우스</option>
                <option value='MONITER'>모니터</option>
                <option value='LIFE'>생활용품</option>
                <option value='ETC'>기타용품</option>
              </LeftItemCategory>
              <LeftItemPrice
                type='number'
                placeholder='판매가격'
                name='price'
                onChange={onChangePrice}
              ></LeftItemPrice>
              <RightItemImage>
                {' '}
                <input
                  type='file'
                  accept='image/*'
                  name='s3uploadMain'
                  id='s3uploadMain'
                  onChange={savethumbnailPhoto}
                />
              </RightItemImage>
            </CreateAndUpdateDivItemUpLeft>
            {/* <CreateAndUpdateDivItemUpRight></CreateAndUpdateDivItemUpRight> */}
          </CreateAndUpdateDivItemUp>
          <CreateAndUpdateDivItemDown>
            <CKEditor
              editor={ClassicEditor}
              data='
              <ul>
              <li>상품명</li>
              <li>구매시기</li>
              <li>사용기간</li>
              <li>하자여부</li>
              </ul>
              <br/>
              * 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br/>
              * 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br/>
              * 영산 URL 첨부는 하나만 해주세요.<br/>
              * 유튜브 영상 첨부시 URL이 아닌, "공유 - 퍼가기" 에 나타나는 src 링크를 입력해주세요.
              <br/><br/>
              안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.
              '
              // @ts-ignore
              onReady={(editor) => {
                // You can store the "editor" and use when it is needed.
                // console.log('Editor is ready to use!', editor);
              }}
              // @ts-ignore
              onChange={(event, editor) => {
                const inputData = editor.getData();
                // console.log({ event, editor, inputData });
                onChangeContent(inputData);
              }}
              // @ts-ignore
              onBlur={(event, editor) => {
                // console.log('Blur.', editor);
              }}
              // @ts-ignore
              onFocus={(event, editor) => {
                // console.log('Focus.', editor);
              }}
            />
          </CreateAndUpdateDivItemDown>
        </CreateAndUpdateDivItem>
      </CreateAndUpdateDiv>
    </CreateAndUpdateFrame>
  );
};

const TradeUpdate = () => {
  const user = localStorage.getItem('userNo');
  const FILE_SIZE_MAX_LIMIT = 100 * 1024 * 1024;
  const [thumbnailPhoto, setthumbnailPhoto] = useState<File | null>(null);
  const accessToken = sessionStorage.getItem('accessToken');
  axios.defaults.headers.common['Authorization'] = `${accessToken}`;
  // const [data, setData] = useState<string>('');
  //axios 전달을 위한 데이터 key값을 먼저 지정해둠
  // @ts-ignore
  const [product, setProduct] = useState<Product>({
    userNo: user,
    productCategory: '',
    title: '',
    content: '',
    price: 0,
    saleStatus: 'READY'
  });
  // 이후 product input에 작성한 내용을 다시 전달함
  // @ts-ignore
  const { userNo, productCategory, title, content, price, saleStatus } =
    product;

  const navigate = useNavigate();
  // 뒤로가기 버튼
  const backToMain = () => {
    navigate('/tradeMain');
  };

  // --- 함수 영역 ---

  const savethumbnailPhoto = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files !== null) {
      if (event.target.files[0].size > FILE_SIZE_MAX_LIMIT) {
        event.target.value = '';
        alert('업로드 가능한 최대 용량은 100MB입니다. ');
        return;
      } else {
        const thumbnailfile = event.target.files[0];
        // console.log(thumbnailfile);
        if (thumbnailfile) {
          setthumbnailPhoto(thumbnailfile);
        } else {
          setthumbnailPhoto(null);
        }
      }
    }
  };

  const saveProduct = async (e: any) => {
    e.preventDefault();
    if (!title || !productCategory || !price) {
      alert('모든 칸을 입력해주세요.');
    } else {
      const data = new FormData();
      const productInfo = JSON.stringify(product);
      const productblob = new Blob([productInfo], {
        type: 'application/json'
      });
      data.append('usedProductSaveRequest', productblob);
      data.append('s3uploadMain', thumbnailPhoto as File);
      // console.log(data);
      try {
        await axios.post('/usedproduct', data, {
          headers: {
            'Content-Type': 'multipart/form-data',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Credentials': 'true'
          }
        });
        // console.log('요청성공');
        alert('등록되었습니다.');
        navigate('/tradeMain');
      } catch (error) {
        console.log(error);
      }
    }
  };

  const onChangeTitle = (e) => {
    product.title = e.target.value;
    // console.log(product);
  };
  const onChangeCategory = (e) => {
    product.productCategory = e.target.value;
    // console.log(product);
  };
  const onChangePrice = (e) => {
    product.price = e.target.value;
    // console.log(product);
  };
  const onChangeContent = (inputData) => {
    product.content = inputData;
    // console.log(product);
  };

  return (
    <CreateAndUpdateFrame>
      <CreateAndUpdateDiv>
        <CreateAndUpdateDivHeader>
          <CreateAndUpdateDivHeaderItem>상품수정</CreateAndUpdateDivHeaderItem>
          <div>
            <BackButton onClick={backToMain}>뒤로가기</BackButton>
            <CreateAndUpdateButton onClick={saveProduct}>
              수정
            </CreateAndUpdateButton>
          </div>
        </CreateAndUpdateDivHeader>
        <CreateAndUpdateDivItem>
          <CreateAndUpdateDivItemUp>
            <CreateAndUpdateDivItemUpLeft>
              <LeftItemName
                type='text'
                placeholder='상품명'
                name='title'
                onChange={onChangeTitle}
              ></LeftItemName>
              <LeftItemCategory
                name='productCategory'
                id='level'
                onChange={onChangeCategory}
              >
                <option value=''>선택</option>
                <option value='KEYBOARD'>키보드</option>
                <option value='MOUSE'>마우스</option>
                <option value='MONITER'>모니터</option>
                <option value='LIFE'>생활용품</option>
                <option value='ETC'>기타용품</option>
              </LeftItemCategory>
              <LeftItemPrice
                type='number'
                placeholder='판매가격'
                name='price'
                onChange={onChangePrice}
              ></LeftItemPrice>
              <RightItemImage>
                {' '}
                <input
                  type='file'
                  accept='image/*'
                  name='s3uploadMain'
                  id='s3uploadMain'
                  onChange={savethumbnailPhoto}
                />
              </RightItemImage>
            </CreateAndUpdateDivItemUpLeft>
            {/* <CreateAndUpdateDivItemUpRight></CreateAndUpdateDivItemUpRight> */}
          </CreateAndUpdateDivItemUp>
          <CreateAndUpdateDivItemDown>
            <CKEditor
              editor={ClassicEditor}
              data='
              <ul>
              <li>상품명</li>
              <li>구매시기</li>
              <li>사용기간</li>
              <li>하자여부</li>
              </ul>
              <br/><br/>
              * 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br/>
              * 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br/>
              * 영산 URL 첨부는 하나만 해주세요.<br/>
              * 유튜브 영상 첨부시 URL이 아닌, "공유 - 퍼가기" 에 나타나는 src 링크를 입력해주세요.
              <br/><br/>
              안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.
              '
              // @ts-ignore
              onReady={(editor) => {
                // You can store the "editor" and use when it is needed.
                // console.log('Editor is ready to use!', editor);
              }}
              // @ts-ignore
              onChange={(event, editor) => {
                const inputData = editor.getData();
                // console.log({ event, editor, inputData });
                onChangeContent(inputData);
              }}
              // @ts-ignore
              onBlur={(event, editor) => {
                // console.log('Blur.', editor);
              }}
              // @ts-ignore
              onFocus={(event, editor) => {
                // console.log('Focus.', editor);
              }}
            />
          </CreateAndUpdateDivItemDown>
        </CreateAndUpdateDivItem>
      </CreateAndUpdateDiv>
    </CreateAndUpdateFrame>
  );
};

export { TradeCreate, TradeUpdate };
