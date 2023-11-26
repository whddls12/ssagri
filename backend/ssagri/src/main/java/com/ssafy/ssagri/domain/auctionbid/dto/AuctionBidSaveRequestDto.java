package com.ssafy.ssagri.domain.auctionbid.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionBidSaveRequestDto {
    //입찰자
    private Long userNo;
    //경매 상품
    private Long auctionNo;
    //입찰 금액
    private int auctionBidPrice;

    @Builder
    public AuctionBidSaveRequestDto(Long userNo, Long auctionNo, int auctionBidPrice) {
        this.userNo = userNo;
        this.auctionNo = auctionNo;
        this.auctionBidPrice = auctionBidPrice;
    }

}
