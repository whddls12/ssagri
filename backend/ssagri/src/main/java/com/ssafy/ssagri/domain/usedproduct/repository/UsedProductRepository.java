package com.ssafy.ssagri.domain.usedproduct.repository;

import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedProductRepository extends JpaRepository<UsedProduct, Long> ,UsedProductCustomRepository{
}
