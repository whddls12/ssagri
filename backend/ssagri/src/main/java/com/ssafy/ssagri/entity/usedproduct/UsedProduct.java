package com.ssafy.ssagri.entity.usedproduct;

import com.ssafy.ssagri.domain.usedproduct.dto.response.UsedProductDetailResponseDto;
import com.ssafy.ssagri.domain.usedproduct.dto.response.UsedProductResponseDto;
import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.Region;
import com.ssafy.ssagri.entity.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "used_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_product_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_product_category",nullable = false)
    private ProductCategory category;

    @Column(name = "used_product_title",nullable = false)
    private String title;

    @Column(name = "used_product_content",length = 1000)
    private String content;

    @Column(name = "used_product_price",nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_product_status",nullable = false)
    private SaleStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "used_product_region",nullable = false)
    private Region region;

    @Column(name = "used_product_likeCount", nullable = false)
    @ColumnDefault("0")
    private int likeCount;



    @Builder
    public UsedProduct(Long no, User user, ProductCategory category, String title, String content, int price, SaleStatus status, Region region, int likeCount) {
        this.no = no;
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.price = price;
        this.status = status;
        this.region = region;
        this.likeCount = likeCount;
    }





    public UsedProductResponseDto toResponse(){
        return UsedProductResponseDto.builder()
                .productNo(this.no)
                .productCategory(this.category)
                .title(this.title)
                .price(this.price)
                .saleStatus(this.status)
                .region(this.region)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .build();
    }

    public UsedProductDetailResponseDto toDetailResponse(User user){
        return UsedProductDetailResponseDto.builder()
                .productNo(this.no)
                .productCategory(this.category)
                .title(this.title)
                .content(this.content)
                .price(this.price)
                .saleStatus(this.status)
                .region(this.region)
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .likeCount(this.likeCount)
                .userNo(user.getNo())
                .userNickname(user.getNickname())
                .userProfile(user.getProfile())
                .userTemper(user.getTemper())
                .build();
    };

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
