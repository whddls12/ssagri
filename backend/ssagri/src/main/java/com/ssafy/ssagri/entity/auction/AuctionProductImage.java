package com.ssafy.ssagri.entity.auction;

import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "auction_product_photo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionProductImage {


    // 경매 이미지 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_product_photo_no")
    private Long no;

    // 경매 상품 no (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_product_no",nullable = false)
    private AuctionProduct auctionProductNo;

    // 경매
    @Column(name = "auction_product_photo_link",nullable = false, length = 1000)
    private String imageLink;

    @Builder
    public AuctionProductImage(AuctionProduct auctionProductNo, String imageLink){
        this.auctionProductNo = auctionProductNo;
        this.imageLink = imageLink;
    }


}
