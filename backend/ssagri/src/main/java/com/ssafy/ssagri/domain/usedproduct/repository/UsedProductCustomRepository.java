package com.ssafy.ssagri.domain.usedproduct.repository;

import com.ssafy.ssagri.entity.usedproduct.ProductCategory;
import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import com.ssafy.ssagri.entity.user.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsedProductCustomRepository {
    Page<UsedProduct> selectAllUsedProduct(ProductCategory productCategory, Region region,String search ,Pageable pageable);
    Page<UsedProduct> selectUsedProductByUserNo(Long userNo, Pageable pageable);
}
