import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import axios from 'axios';
// import ClassicEditor from './ckeditor'; // -> tradeCreate에서 만들면, 만들 필요 없다.

// ClassicEditor
//   // Note that you do not have to specify the plugin and toolbar configuration — using defaults from the build.
//   .create(document.querySelector('#ckeditor') as HTMLElement, {
//     initialData: '<h2>Initial data</h2><p>Foo bar.</p>'
//   })
//   .then((editor) => {
//     console.log('Editor was initialized', editor);
//   })
//   .catch((error) => {
//     console.error(error.stack);
//   });

// axios 설정
// axios.defaults.baseURL = 'http://localhost:5000/api'; // 로컬 테스트 URL
axios.defaults.baseURL = 'https://j9b209.p.ssafy.io/api';
// axios.defaults.baseURL = 'http://192.168.31.55/api';
axios.defaults.withCredentials = true; // refreshToken cookie를 주고받기 위함

// 라우터 - 주소경로 생성
import { BrowserRouter } from 'react-router-dom';
// 리코일 - 상태정보, 비동기함수 관리
import { RecoilRoot } from 'recoil';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <RecoilRoot>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </RecoilRoot>
);
