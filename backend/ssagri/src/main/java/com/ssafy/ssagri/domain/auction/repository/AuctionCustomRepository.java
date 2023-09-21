package com.ssafy.ssagri.domain.auction.repository;

import com.querydsl.core.Tuple;
import com.ssafy.ssagri.entity.auction.AuctionProduct;

import java.util.List;

public interface AuctionCustomRepository {


    // 모든 경매리스트 출력 (상품정보)
    void getAuctionProducts();
}
