package com.ssafy.ssagri.domain.usedproductphoto.repository;

import com.ssafy.ssagri.entity.usedproduct.UsedProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedProductPhotoRepository extends JpaRepository<UsedProductPhoto,Long>,UsedProductPhotoCustomRepository {
}
