package com.ssafy.ssagri.domain.auctionbid.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionBidSocketRequestDto {

    private Long auctionBidNo;

    @Builder
    public AuctionBidSocketRequestDto(Long auctionBidNo) {
        this.auctionBidNo = auctionBidNo;
    }
}
