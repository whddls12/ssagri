package com.ssafy.ssagri.entity.usedproduct;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "used_product_photo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_product_photo_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_product_no",nullable = false)
    private UsedProduct usedProduct;


    @Column(name = "used_product_photo_link",nullable = false)
    private String usedProductPhotoLink;

    @Column(name = "used_product_photo_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private PhotoType  usedProductPhotoType;

    @Builder
    public UsedProductPhoto(Long no, UsedProduct usedProduct, String usedProductPhotoLink, PhotoType usedProductPhotoType) {
        this.no = no;
        this.usedProduct = usedProduct;
        this.usedProductPhotoLink = usedProductPhotoLink;
        this.usedProductPhotoType = usedProductPhotoType;
    }
}
