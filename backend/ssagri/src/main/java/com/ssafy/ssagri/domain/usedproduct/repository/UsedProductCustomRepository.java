package com.ssafy.ssagri.domain.usedproduct.repository;

import com.ssafy.ssagri.domain.usedproduct.dto.response.UsedProductResponse;
import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsedProductCustomRepository {
    Page<UsedProduct> selectAllUsedProduct(Pageable pageable);
}
