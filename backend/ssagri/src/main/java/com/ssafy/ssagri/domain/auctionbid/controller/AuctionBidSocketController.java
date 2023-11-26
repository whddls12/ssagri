package com.ssafy.ssagri.domain.auctionbid.controller;

import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSaveRequestDto;
import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSocketRequestDto;
import com.ssafy.ssagri.domain.auctionbid.service.AuctionBidService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auctionbid-socket")
@Api(tags = {"[AUCTION BID SOCKET] 경매 입찰을 실시간으로 보기 위한 API"})
@Slf4j
public class AuctionBidSocketController {

    private final AuctionBidService auctionBidService;

    @MessageMapping("/auction/{auctionNo}")
    @SendTo("/sub/auction/{auctionNo}")
    @Operation(summary = "실시간으로 경매 입찰 띄워주는 메서드")
    public ResponseEntity<?> sendBid(AuctionBidSocketRequestDto auctionBidSocketRequestDto){
        log.info("auctionBidSocketRequestDto = {}",auctionBidSocketRequestDto);
        return ResponseEntity.ok(auctionBidService.changeResponseDto(auctionBidSocketRequestDto));
    }
}
