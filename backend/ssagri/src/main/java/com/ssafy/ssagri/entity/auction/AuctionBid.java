package com.ssafy.ssagri.entity.auction;

import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSelectResponseDto;
import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.FetchType;

@Entity
@Getter
@Table(name = "auction_bid")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionBid extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_bid_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_product_bidder_no",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_product_no",nullable = false)
    private AuctionProduct auctionProduct;


    @Column(name = "auction_bid_price",nullable = false)
    private int price;


    @Builder
    public AuctionBid(Long no, User user, AuctionProduct auctionProduct, int price) {
        this.no = no;
        this.user = user;
        this.auctionProduct = auctionProduct;
        this.price = price;
    }

    public AuctionBidSelectResponseDto toResponse(){
        return AuctionBidSelectResponseDto.builder()
                .userNo(this.user.getNo())
                .userNickname(this.user.getNickname())
                .userProfileImageLink(this.user.getProfile())
                .auctionProductNo(this.auctionProduct.getNo())
                .auctionBidPrice(this.price)
                .auctionBidDate(this.getCreateDate())
                .build();
    }
}
