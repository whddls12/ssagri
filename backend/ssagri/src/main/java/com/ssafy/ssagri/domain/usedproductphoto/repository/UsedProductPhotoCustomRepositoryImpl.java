package com.ssafy.ssagri.domain.usedproductphoto.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ssafy.ssagri.domain.usedproductphoto.dto.QUsedProductPhotoResponseDto;
import com.ssafy.ssagri.domain.usedproductphoto.dto.UsedProductPhotoResponseDto;
import com.ssafy.ssagri.entity.usedproduct.PhotoType;
import com.ssafy.ssagri.entity.usedproduct.QUsedProductPhoto;
import com.ssafy.ssagri.entity.usedproduct.UsedProductPhoto;

import javax.persistence.EntityManager;
import java.util.List;

public class UsedProductPhotoCustomRepositoryImpl implements UsedProductPhotoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    QUsedProductPhoto usedProductPhoto = QUsedProductPhoto.usedProductPhoto;

    public UsedProductPhotoCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    /*
    * 서브 사진 가져오는 것
    * */
    @Override
    public List<UsedProductPhotoResponseDto> selectSubPhotoByProductNo(Long usedProductNo) {
        List<UsedProductPhotoResponseDto> fetch = jpaQueryFactory.select(new QUsedProductPhotoResponseDto(usedProductPhoto.no, usedProductPhoto.usedProductPhotoLink))
                .from(usedProductPhoto)
                .where(usedProductPhoto.usedProduct.no.eq(usedProductNo)
                        .and(usedProductPhoto.usedProductPhotoType.eq(PhotoType.SUB)))
                .orderBy(usedProductPhoto.no.asc())
                .fetch();
        return fetch;
    }

    /*메인사진 가져오는것
    * */
    @Override
    public UsedProductPhotoResponseDto selectMainPhotoByProductNo(Long usedProductNo) {
        UsedProductPhotoResponseDto usedProductPhotoResponseDto = jpaQueryFactory.select(new QUsedProductPhotoResponseDto(usedProductPhoto.no, usedProductPhoto.usedProductPhotoLink))
                .from(usedProductPhoto)
                .where(usedProductPhoto.usedProduct.no.eq(usedProductNo)
                        .and(usedProductPhoto.usedProductPhotoType.eq(PhotoType.MAIN)))
                .orderBy(usedProductPhoto.no.asc())
                .fetchOne();
        return usedProductPhotoResponseDto;
    }
}
