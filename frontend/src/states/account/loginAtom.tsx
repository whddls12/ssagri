import { atom } from 'recoil';

// 로그인 여부
export const isLoggedInAtom = atom({
  key: 'isLoggedIn',
  default: false
});
