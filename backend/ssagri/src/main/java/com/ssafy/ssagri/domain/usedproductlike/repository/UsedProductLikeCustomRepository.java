package com.ssafy.ssagri.domain.usedproductlike.repository;

public interface UsedProductLikeCustomRepository {
    boolean checkLikeByUserNo(Long userNo, Long usedProductNo);
}
