import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();
// 로그인 여부
export const isLoggedInAtom = atom({
  key: 'isLoggedIn',
  default: false,
  effects_UNSTABLE: [persistAtom]
});
