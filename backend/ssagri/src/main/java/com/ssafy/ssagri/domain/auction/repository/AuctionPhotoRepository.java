package com.ssafy.ssagri.domain.auction.repository;

import com.ssafy.ssagri.entity.auction.AuctionProduct;
import com.ssafy.ssagri.entity.auction.AuctionProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionPhotoRepository extends JpaRepository<AuctionProductPhoto, Long> {

    List<AuctionProductPhoto> findByAuctionProduct(AuctionProduct no);
}
