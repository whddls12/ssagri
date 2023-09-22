export type ProductItemType = {
  createDate: string;
  like: boolean;
  likeCount: number;
  price: number;
  productCategory: string;
  productNo: number;
  region: string;
  saleStatus: string;
  title: string;
  updateDate: string;
  usedProductPhotoResponseDto: {
    link: string;
    photoNo: number;
  };
};

// ProductItemType에서 유저 정보 확장
export type ProductItemDetailType = ProductItemType & {
  userNickname: 'string';
  userNo: 0;
  userProfile: 'string';
  userTemper: 0;
};
