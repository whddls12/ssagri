package com.ssafy.ssagri.domain.auction.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;


public class AuctionCustomRepositoryImpl implements AuctionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public AuctionCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public void getAuctionProducts() {

    }


    // 모든 경매리스트 출력 (상품정보)
   // @Override
   // public void getAuctionProducts(){


//        jpaQueryFactory
//                .select(qAuctionProduct., qAuctionProductPhoto)
//                .from(qAuctionProductPhoto)
//                .rightJoin(qAuctionProductPhoto.auctionProduct,qAuctionProduct)
//                .fetchJoin()
//                .fetch();


   // }





}
