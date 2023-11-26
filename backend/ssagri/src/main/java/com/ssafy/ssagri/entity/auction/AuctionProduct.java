package com.ssafy.ssagri.entity.auction;

import com.ssafy.ssagri.entity.common.BaseTimeEntity;
import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "auction_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionProduct extends BaseTimeEntity {

    //경매 상품 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_product_no")
    private Long no;


    // 경매 개최자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no",nullable = false)
    private User user;

    // 경매 상품 이름
    @Column(name = "auction_product_name",nullable = false)
    private String name;

    // 경매 상한가
    @Column(name = "auction_product_up_price",nullable = false)
    private int upPrice;

    // 경매 하한가
    @Column(name = "auction_product_down_price",nullable = false)
    private int downPrice;

    // 경매 입찰 단위
    @Column(name = "auction_product_price_count",nullable = false)
    private int priceCount;

    // 경매 시작시간
    @Column(name = "auction_product_start_date",nullable = false)
    private LocalDateTime startDate;

    // 경매 마감시간
    @Column(name = "auction_product_end_date",nullable = false)
    private LocalDateTime endDate;


    // 경매 설명
    @Column(name = "auction_product_comment")
    private String comment;

    // 경매 상태
    @Enumerated(EnumType.STRING)
    @Column(name = "auction_product_status")
    private AuctionStatus auctionStatus;

    // 경매 입찰가
    @Column(name = "auction_product_finally_price")
    private int finallyPrice;

    // 경매 분류 (모니터, 키보드 ,,,)
    @Enumerated(EnumType.STRING)
    @Column(name = "auction_product_type")
    private AuctionProductType type;

    // 경매 정가
    @Column(name = "auction_product_origin_price")
    private int originPrice;

    @OneToMany(mappedBy = "auctionProductNo", fetch = FetchType.LAZY)
    List<AuctionProductImage> images = new ArrayList<>();

    @Builder
    public AuctionProduct(int finallyPrice,LocalDateTime modifyTime ,AuctionStatus auctionStatus, LocalDateTime deleteDate, LocalDateTime createTime ,int originPrice, AuctionProductType type,User user, String name, int upPrice, int downPrice, int priceCount, LocalDateTime startDate, LocalDateTime endDate, String comment) {
        this.user = user;
        this.name = name;
        this.upPrice = upPrice;
        this.downPrice = downPrice;
        this.priceCount = priceCount;
        this.startDate = startDate;
        this.setDeleteDate(deleteDate);
        this.endDate = endDate;
        this.comment = comment;
        this.auctionStatus = auctionStatus;
        this.originPrice = originPrice;
        this.type = type;
        this.setCreateDate(createTime);
        this.finallyPrice =finallyPrice;
        this.setUpdateDate(modifyTime);
        }

        public void setFinallyPrice(int finallyPrice){
            this.finallyPrice = finallyPrice;
        }
}
