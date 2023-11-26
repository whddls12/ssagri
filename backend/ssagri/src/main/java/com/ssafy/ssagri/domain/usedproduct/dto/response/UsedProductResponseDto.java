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
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductResponseDto {
    private Long productNo;
    private ProductCategory productCategory;
    private String title;
    private int price;
    private SaleStatus saleStatus;
    private Region region;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private UsedProductPhotoResponseDto usedProductPhotoResponseDto;

    private boolean isLike;

    @Builder

    public UsedProductResponseDto(Long productNo, ProductCategory productCategory, String title, int price, SaleStatus saleStatus, Region region, LocalDateTime createDate, LocalDateTime updateDate, UsedProductPhotoResponseDto usedProductPhotoResponseDto, boolean isLike) {
        this.productNo = productNo;
        this.productCategory = productCategory;
        this.title = title;
        this.price = price;
        this.saleStatus = saleStatus;
        this.region = region;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.usedProductPhotoResponseDto = usedProductPhotoResponseDto;
        this.isLike = isLike;
    }
}
