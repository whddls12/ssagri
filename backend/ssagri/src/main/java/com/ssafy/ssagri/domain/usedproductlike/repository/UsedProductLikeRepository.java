package com.ssafy.ssagri.domain.usedproductlike.repository;

import com.ssafy.ssagri.domain.usedproduct.repository.UsedProductCustomRepository;
import com.ssafy.ssagri.entity.usedproduct.UsedProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedProductLikeRepository extends JpaRepository<UsedProductLike,Long>, UsedProductLikeCustomRepository {

}
