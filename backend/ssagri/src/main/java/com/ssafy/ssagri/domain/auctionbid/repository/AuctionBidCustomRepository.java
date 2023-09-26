package com.ssafy.ssagri.domain.auctionbid.repository;

import com.ssafy.ssagri.entity.auction.AuctionBid;

import java.util.List;

public interface AuctionBidCustomRepository {
    public List<AuctionBid> selectAuctionBidByAuctionProduct(Long auctionProductNo);
}
