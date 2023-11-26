import axios from 'axios';
import base64 from 'base-64';
import { isLoggedInAtom } from '../states/account/loginAtom';
// 액세스토큰 만료 시간
const JWT_EXPIRY_TIME = 24 * 3600 * 1000; // 만료 시간 (24시간)

// 로그인 성공
const onLoginSuccess = (response: any) => {
  // 로컬 스토리지에 로그인 여부 저장 (새로고침 시 날아가는 것 때문)
  localStorage.setItem('isLoggedIn', 'true');

  const accessToken = response.headers['access-token'];
  // axios 헤더에 jwt 토큰 담기
  axios.defaults.headers.common['Authorization'] = accessToken;

  sessionStorage.setItem('accessToken', accessToken);

  // userNo를 localStorage에 넣기
  let payload = accessToken.substring(
    accessToken.indexOf('.') + 1,
    accessToken.lastIndexOf('.')
  );
  let dec = base64.decode(payload);
  let userNo = JSON.parse(dec).userNo;
  localStorage.setItem('userNo', userNo);

  // 액세스토큰 만료하기 전에 로그인 연장
  setTimeout(onSilentRefresh, JWT_EXPIRY_TIME - 60000);
};

// silent Refresh
// 액세스 토큰이 만료되었을 때, 새로고침 되었을 때 사용
const onSilentRefresh = () => {
  if (isLoggedInAtom) {
    localStorage.removeItem('isLoggedIn');

    axios
      .get('/jwt/refill')
      .then((res) => {
        console.log('silent refresh, 새로운 액세스 토큰 발급');
        // 리프레시 토큰이 유효 [ STATUS 200 ]
        // 새로운 액세스 토큰 발급
        onLoginSuccess(res);
      })
      .catch(() => {
        console.log('silent refresh, 리프레시 토큰이 유효하지 않습니다.');
        // 리프레시 토큰이 유효하지 않은 경우 [ STATUS 400, 500 ]
        // 로그인페이지로 이동
      });
  }
};

const onLogout = () => {
  axios
    .get('/user/logout')
    .then((res) => {
      console.log(res);
      // axios의 헤더에 AccessToken 초기화
      delete axios.defaults.headers.common['Authorization'];
      // 로컬스토리지 로그인여부 삭제
      localStorage.removeItem('isLoggedIn');
      localStorage.removeItem('userNo');
      // cookie에 저장된 RefreshToken 삭제
      // 클라이언트 측에서 불가, 서버 측에서 만료시간을 변경하는 등의 방식으로 해결
    })
    .catch((err) => {
      console.log(err);
    });
};

export { onLoginSuccess, onSilentRefresh, onLogout };
