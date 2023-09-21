package com.ssafy.ssagri.domain.usedproductphoto.repository;

import com.ssafy.ssagri.domain.usedproduct.dto.response.UsedProductResponseDto;
import com.ssafy.ssagri.domain.usedproductphoto.dto.UsedProductPhotoResponseDto;

import java.util.List;

public interface UsedProductPhotoCustomRepository {
    List<UsedProductPhotoResponseDto> selectSubPhotoByProductNo(Long usedProductNo);

    UsedProductPhotoResponseDto selectMainPhotoByProductNo(Long usedProductNo);
}
