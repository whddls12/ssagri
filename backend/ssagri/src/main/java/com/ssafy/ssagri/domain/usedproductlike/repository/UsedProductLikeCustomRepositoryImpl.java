package com.ssafy.ssagri.domain.usedproductlike.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssagri.entity.usedproduct.QUsedProductLike;
import com.ssafy.ssagri.entity.usedproduct.UsedProductLike;

import javax.persistence.EntityManager;
import java.util.Optional;

public class UsedProductLikeCustomRepositoryImpl implements UsedProductLikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QUsedProductLike usedProductLike = QUsedProductLike.usedProductLike;

    public UsedProductLikeCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public boolean checkLikeByUserNo(Long userNo, Long usedProductNo) {
        UsedProductLike like = jpaQueryFactory.selectFrom(usedProductLike)
                .where(usedProductLike.user.no.eq(userNo).and(usedProductLike.usedProduct.no.eq(usedProductNo)))
                .fetchOne();
        return like == null ? false : true;
    }

    @Override
    public Optional<UsedProductLike> findLikeByUserNoAndUsedProductNo(Long userNo, Long usedProductNo) {
        UsedProductLike usedproductLike = jpaQueryFactory.selectFrom(usedProductLike)
                .where(usedProductLike.user.no.eq(userNo).and(usedProductLike.usedProduct.no.eq(usedProductNo)))
                .fetchOne();
        return Optional.ofNullable(usedproductLike);
    }


}
