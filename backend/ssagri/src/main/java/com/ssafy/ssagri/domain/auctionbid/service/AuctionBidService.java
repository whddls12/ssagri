package com.ssafy.ssagri.domain.auctionbid.service;

import com.ssafy.ssagri.domain.auction.repository.AuctionRepository;
import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSaveRequestDto;
import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSelectResponseDto;
import com.ssafy.ssagri.domain.auctionbid.dto.AuctionBidSocketRequestDto;
import com.ssafy.ssagri.domain.auctionbid.repository.AuctionBidRepository;
import com.ssafy.ssagri.domain.notification.NotificationService;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.entity.auction.AuctionBid;
import com.ssafy.ssagri.entity.auction.AuctionProduct;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuctionBidService {
    private final AuctionBidRepository auctionBidRepository;
    private final UserRegistAndModifyRepository userRegistAndModifyRepository;
    private final AuctionRepository auctionRepository;
    private final NotificationService notificationService;


    /*
    * 새롭게 입찰하는 메서드
    * */
    @Transactional
    public Long save(AuctionBidSaveRequestDto auctionBidSaveRequestDto){
        log.info("AuctionBidService save");
        log.info("auctionBidSaveRequestDto = {}",auctionBidSaveRequestDto);
        //User, AuctionProduct 조회한다.
        Optional<AuctionProduct> findAuction = auctionRepository.findById(auctionBidSaveRequestDto.getAuctionNo());
        Optional<User> findUser = userRegistAndModifyRepository.findById(auctionBidSaveRequestDto.getUserNo());
        AuctionProduct auctionProduct = null;
        User user = null;

        //조회 했을 때 없으면 예외발생
        if(findAuction.isPresent()){
            auctionProduct = findAuction.get();
        }else {
            throw new CustomException(CustomExceptionStatus.AUCTION_PRODUCT_DOES_NOT_EXIST);
        }
        if(findUser.isPresent()){
            user = findUser.get();
        }else{
            throw new CustomException(CustomExceptionStatus.USER_DOES_NOT_EXSIST);
        }
        //만약 입찰자가 경매 올린 본인이면 예외
        if(user.getNo().equals(auctionProduct.getUser().getNo())){
            throw new CustomException(CustomExceptionStatus.AUCTION_BIDBER_SAME);
        }


        //비드 Entity 만들어 주고 DB에 저장
        AuctionBid auctionBid = AuctionBid.builder()
                .auctionProduct(auctionProduct)
                .user(user)
                .price(auctionBidSaveRequestDto.getAuctionBidPrice())
                .build();

        auctionProduct.setFinallyPrice(auctionBidSaveRequestDto.getAuctionBidPrice());

        auctionBidRepository.save(auctionBid);

        //이 Auction에 입찰했던 사람들한테 메시지 보내주기
        notificationService.sendMessageToBidder(auctionProduct.getNo(),user.getNo(),user.getNickname(),auctionBidSaveRequestDto.getAuctionBidPrice());

        return auctionBid.getNo();
    }

    public List<AuctionBidSelectResponseDto> selectAuctionBid(Long auctionProductNo){
        //경매상품에 대한 비드 정보 조회
        List<AuctionBid> auctionBidList = auctionBidRepository.selectAuctionBidByAuctionProduct(auctionProductNo);

        //비드 ResponseDto List 생성
        List<AuctionBidSelectResponseDto> auctionBidResponseDtoList = new ArrayList<>();

        //Response로 변환
        for (AuctionBid auctionBid : auctionBidList) {
            AuctionBidSelectResponseDto responseDto = auctionBid.toResponse();

            auctionBidResponseDtoList.add(responseDto);
        }
        //ResponseList 반환
        return auctionBidResponseDtoList;
    }

    public AuctionBidSelectResponseDto changeResponseDto(AuctionBidSocketRequestDto auctionBidSocketRequestDto) {
        AuctionBid auctionBid = auctionBidRepository.findById(auctionBidSocketRequestDto.getAuctionBidNo())
                .orElseThrow(()->new CustomException(CustomExceptionStatus.AUCTION_BID_DOES_NOT_EXIST));
        return auctionBid.toResponse();
    }
}
