package com.ssafy.ssagri.domain.usedproductlike.repository;

import com.ssafy.ssagri.entity.usedproduct.UsedProductLike;

import java.util.Optional;

public interface UsedProductLikeCustomRepository {
    boolean checkLikeByUserNo(Long userNo, Long usedProductNo);
    Optional<UsedProductLike> findLikeByUserNoAndUsedProductNo(Long userNo, Long usedProductNo);


}
