package com.ssafy.ssagri.domain.auctionbid.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssagri.entity.auction.AuctionBid;
import com.ssafy.ssagri.entity.auction.QAuctionBid;

import javax.persistence.EntityManager;
import java.util.List;

public class AuctionBidCustomRepositoryImpl implements AuctionBidCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    QAuctionBid auctionBid = QAuctionBid.auctionBid;

    public AuctionBidCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<AuctionBid> selectAuctionBidByAuctionProduct(Long auctionProductNo) {
        List<AuctionBid> findAuctionBidList = jpaQueryFactory.selectFrom(auctionBid)
                .where(auctionBid.auctionProduct.no.eq(auctionProductNo))
                .orderBy(auctionBid.price.desc(),auctionBid.no.desc()).fetch();
        return findAuctionBidList;

    }
}
