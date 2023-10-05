package com.ssafy.ssagri.domain.auction.dto;

import com.ssafy.ssagri.entity.auction.AuctionProduct;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
public class Images {

    // 경매 이미지 no
    private Long no;

    // 경매 상품 no (FK)
    private Long auctionProductNo;

    // 경매
    private String imageLink;

}
