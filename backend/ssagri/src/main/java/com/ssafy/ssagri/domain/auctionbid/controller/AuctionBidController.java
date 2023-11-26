package com.ssafy.ssagri.domain.auctionbid.controller;

import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSaveRequestDto;
import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSelectResponseDto;
import com.ssafy.ssagri.domain.auctionbid.service.AuctionBidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auction-bid")
@CrossOrigin("*")
@Api(tags = {"[AUCTION BID]경매 입찰에 대한 API"})
@Slf4j
public class AuctionBidController {
    private final AuctionBidService auctionBidService;

    @PostMapping("")
    @Operation(summary = "경매 상품에 입찰하는 메서드", description = "입찰자No, 경매상품No, 입찰금액을 넘겨줘야 한다.")
    public ResponseEntity<Long> saveAuctionBid(@RequestBody AuctionBidSaveRequestDto auctionBidSaveRequestDto){
        Long bidNo = auctionBidService.save(auctionBidSaveRequestDto);
        return ResponseEntity.ok(bidNo);
    }

    @GetMapping("/{auctionProductNo}")
    @Operation(summary = "경매 상품 입찰 내역 조회하는 메서드",description = "경매상품No를 넘겨주면 그 경매의 입찰정보를 List로 넘겨준다.")
    public ResponseEntity<List<AuctionBidSelectResponseDto>> selectAuctionBid(@PathVariable(name = "auctionProductNo") Long auctionProductNo){
        return ResponseEntity.ok(auctionBidService.selectAuctionBid(auctionProductNo));
    }
}
