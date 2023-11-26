package com.ssafy.ssagri.domain.auction.dto;

import com.ssafy.ssagri.entity.auction.AuctionProductType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuctionProductCreateDTO {

    // 경매 주최자 아이디
    private Long userNo;

    // 상품 명
    private String name;

    // 상품 설명
    private String comment;

    // 상태
//    private AuctionStatus status;

    // 경매 시작 시간
    private String startDate;

    // 경매 마감 시간
    private String endDate;

    // 경매 하한가
    private int downPrice;

    // 경매 상한가
//    private int upPrice;

    // 경매 정가
    private int originPrice;

    // 경매 입찰 단위
    private int countPrice;

    // 물품 분류
    private AuctionProductType type;

//    private LocalDateTime modifyDate;


    @Builder
    public AuctionProductCreateDTO(Long userNo, String name, String comment, String startDate, String endDate, int downPrice, int originPrice, int countPrice, AuctionProductType type) {
        this.userNo = userNo;
        this.name = name;
        this.comment = comment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.downPrice = downPrice;
        this.originPrice = originPrice;
        this.countPrice = countPrice;
        this.type = type;
    }
}
