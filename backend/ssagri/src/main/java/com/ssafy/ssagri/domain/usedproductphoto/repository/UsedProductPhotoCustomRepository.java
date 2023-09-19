package com.ssafy.ssagri.domain.usedproductphoto.repository;

import com.ssafy.ssagri.domain.usedproductphoto.dto.UsedProductPhotoResponse;

import java.util.List;

public interface UsedProductPhotoCustomRepository {
    List<UsedProductPhotoResponse> selectPhotoByProductNo(Long usedProductNo);
}
