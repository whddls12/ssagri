package com.ssafy.ssagri.domain.auction.repository;

import com.ssafy.ssagri.entity.auction.AuctionProduct;
import com.ssafy.ssagri.entity.auction.AuctionProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AuctionRepository extends JpaRepository<AuctionProduct, Long>, AuctionCustomRepository {

    List<AuctionProduct> findAllByOrderByEndDateDesc();

AuctionProduct findByNo(Long no);

List<AuctionProduct> findAllByType(AuctionProductType type);

@Query("SELECT a FROM AuctionProduct a where a.name LIKE %:keyword%")
List<AuctionProduct> findAllByName(@Param("keyword") String keyword);




}
