package com.ssafy.ssagri.domain.usedproduct.dto.response;

import com.ssafy.ssagri.domain.usedproductphoto.dto.UsedProductPhotoResponseDto;
import com.ssafy.ssagri.entity.usedproduct.ProductCategory;
import com.ssafy.ssagri.entity.usedproduct.SaleStatus;
import com.ssafy.ssagri.entity.user.Region;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductDetailResponseDto {
    private Long productNo;
    private ProductCategory productCategory;
    private String title;
    private String content;
    private int price;
    private SaleStatus saleStatus;
    private Region region;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private UsedProductPhotoResponseDto usedProductPhotoResponseDto;

    private int likeCount;
    private boolean isLike;

    //유저정보 넣기
    private Long userNo;
    private String userNickname;
    private String userProfile;
    private int userTemper;

    @Builder
    public UsedProductDetailResponseDto(Long productNo, ProductCategory productCategory, String title, String content, int price, SaleStatus saleStatus, Region region, LocalDateTime createDate, LocalDateTime updateDate, UsedProductPhotoResponseDto usedProductPhotoResponseDto, int likeCount, boolean isLike, Long userNo, String userNickname, String userProfile, int userTemper) {
        this.productNo = productNo;
        this.productCategory = productCategory;
        this.title = title;
        this.content = content;
        this.price = price;
        this.saleStatus = saleStatus;
        this.region = region;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.usedProductPhotoResponseDto = usedProductPhotoResponseDto;
        this.likeCount = likeCount;
        this.isLike = isLike;
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.userProfile = userProfile;
        this.userTemper = userTemper;
    }
}
