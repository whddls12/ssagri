package com.ssafy.ssagri.domain.auction.repository;

import com.ssafy.ssagri.entity.auction.AuctionProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuctionRepository extends JpaRepository<AuctionProduct, Long>, AuctionCustomRepository {




}
