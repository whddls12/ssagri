package com.ssafy.ssagri.domain.auction.repository;

import com.ssafy.ssagri.entity.auction.AuctionProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionRepository extends JpaRepository<AuctionProduct, Long>, AuctionCustomRepository {

    List<AuctionProduct> findAll();


}
