package com.ssafy.ssagri.domain.auction.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AuctionProductImageDTO {

    // acution no
    private Long no;

    // 경매 개최한 사람
    private String user;

    // 경매 상품 이름
    private String name;

    // 경매 상한가
    private int upPrice;

    // 경매 하한가
    private int downPrice;

    // 경매 상품 입찰 단위
    private int price;

    // 경매 시작 시간
    private LocalDateTime startDate;

    // 경매 마감 시간
    private LocalDateTime endDate;

    // 경매 상품 설명
    private String comment;

    // 경매 진행 상태
    private String auctionStatus;

    // 경매 입찰가
    private int finallyPrice;

    private LocalDateTime deleteDate;

    private LocalDateTime modifyDate;

    private String type;

    private int originPrice;

    // 경매 이미지
//    private List<> auctionImages;

//    @QueryProjection
//    public AuctionProductImageDTO (Long no, String user, String name ,int upPrice, int downPrice, int price, LocalDateTime startDate, LocalDateTime endDate,
//    String comment, String auctionStatus, int finallyPrice, List<AuctionProductPhoto> auctionImages){
//        this.no = no;
//        this.user = user;
//        this.name = name;
//        this.upPrice = upPrice;
//        this.downPrice = downPrice;
//        this.price = price;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.comment = comment;
//        this.auctionStatus = auctionStatus;
//        this.finallyPrice = finallyPrice;
//        this.auctionImages = auctionImages;
//    }

}
