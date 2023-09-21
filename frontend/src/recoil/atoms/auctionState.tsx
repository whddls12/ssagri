import { atom } from 'recoil';

const RecoilAuctionAtoms = {
  userNoState: atom({
    key: 'userNoState',
    default: 1
  }),

  itemNameState: atom({
    key: 'itemNameState',
    default: ''
  }),

  itemDescriptionState: atom({
    key: 'itemDescriptionState',
    default: ''
  }),

  startTimeState: atom({
    key: 'startTimeState',
    default: new Date()
  })
};

export { RecoilAuctionAtoms };
