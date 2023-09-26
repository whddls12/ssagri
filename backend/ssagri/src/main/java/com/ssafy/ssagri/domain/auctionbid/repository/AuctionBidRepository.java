package com.ssafy.ssagri.domain.auctionbid.repository;

import com.ssafy.ssagri.entity.auction.AuctionBid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionBidRepository extends JpaRepository<AuctionBid,Long>,AuctionBidCustomRepository {
}
