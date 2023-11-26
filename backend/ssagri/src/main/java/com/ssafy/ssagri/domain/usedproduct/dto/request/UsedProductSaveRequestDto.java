package com.ssafy.ssagri.domain.usedproduct.dto.request;

import com.ssafy.ssagri.entity.usedproduct.ProductCategory;
import com.ssafy.ssagri.entity.usedproduct.SaleStatus;
import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import com.ssafy.ssagri.entity.user.User;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductSaveRequestDto {
    private Long userNo;
    private ProductCategory productCategory;
    private String title;
    private String content;
    private int price;
    private SaleStatus saleStatus;

    @Builder
    public UsedProductSaveRequestDto(Long userNo, ProductCategory productCategory, String title, String content, int price, SaleStatus saleStatus) {
        this.userNo = userNo;
        this.productCategory = productCategory;
        this.title = title;
        this.content = content;
        this.price = price;
        this.saleStatus = saleStatus;
    }

    public UsedProduct toEntity(User user){
        UsedProduct usedProduct = UsedProduct.builder()
                .user(user)
                .category(this.productCategory)
                .title(this.title)
                .content(this.content)
                .price(this.price)
                .status(this.saleStatus)
                .region(user.getRegion())
                .likeCount(0)
                .build();
        return usedProduct;
    }
}
