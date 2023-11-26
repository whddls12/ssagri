package com.ssafy.ssagri.domain.notification;

import com.ssafy.ssagri.domain.auctionbid.repository.AuctionBidRepository;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.entity.auction.AuctionBid;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;



@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    // userNo, SseEmitter 이렇게 두개가 들어간다.
    // send할 때는 경매에 참여하고 있는 사람 select해서 보낸다.
    private final Map<Long, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();
    private final AuctionBidRepository auctionBidRepository;
    public SseEmitter addSseEmitter(Long userNo) {
        log.info("userNo = {}", userNo);
        //60분 짜리 SseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(60 * 60 * 1000L);

        log.info("sseEmitter = {}", sseEmitter);

        //더미 메시지 보냄
        try {
            sseEmitter.send(SseEmitter.event().name("sse-emitter-created").data("연결성공"));
        } catch (IOException e) {
            new CustomException(CustomExceptionStatus.SSEEMITTER_CAN_NOT_CREATE);
        }
        //완료,에러,타임아웃 발생시 map에서 제거하도록 이벤트 작성
        sseEmitter.onError((e) -> sseEmitterMap.remove(userNo));
        sseEmitter.onTimeout(() -> sseEmitterMap.remove(userNo));
        sseEmitter.onCompletion(() -> sseEmitterMap.remove(userNo));

        sseEmitterMap.put(userNo, sseEmitter);
        log.info("sseEmitterMap.size() = {}", sseEmitterMap.size());
        return sseEmitter;
    }

    @Transactional(readOnly = true)
    public void sendMessageToBidder(Long auctionNo, Long newBidderNo, String bidderNickname, int price) {
        log.info("sseEmitterMap.size() = {}", sseEmitterMap.size());
        log.info("sendMessageToBidder");

        //메시지 Json으로 만들어 준다.
        //"bidderNickname을 가진 사용자가, price을 입찰했다"는 의미
        String sendingMessage = new JSONObject()
                .put("auctionNo",auctionNo)
                .put("bidderNickname", bidderNickname)
                .put("price", price)
                .toString();

        //AuctionNo에 해당하는 경매에 참여하는 사람을 전부 가져온다.
        List<AuctionBid> auctionBids = auctionBidRepository.selectAuctionBidByAuctionProduct(auctionNo);

        //Set 만들어준다.
        Set<Long> bidderNoSet = new HashSet<>();

        //경매 입찰을 돌면서 Set에 추가해준다.
        for (AuctionBid auctionBid : auctionBids) {
            Long bidderNo = auctionBid.getUser().getNo();
            if(bidderNo!=newBidderNo){
                bidderNoSet.add(bidderNo);
            }
        }

        //SseEmitter Map을 돌면서 Bid한 사람이 있는지 찾음
        for (Long userNo : sseEmitterMap.keySet()) {
            try {
                //비드한 사람의 Emitter를 찾으면 메시지 전송한다.
                if(bidderNoSet.contains(userNo)){
                    log.info("sendingMessage = {}", sendingMessage);
                    sseEmitterMap.get(userNo).send(SseEmitter.event().name("new bid").data(sendingMessage));
                }
            } catch (IOException e) {
                //유효하지 않은 Emitter 제거한다.
                sseEmitterMap.remove(userNo);
                throw new CustomException(CustomExceptionStatus.SSEEMITTER_DOES_NOT_EXIST);
            }
        }
    }
    public void sendMessageToChatter(User sender, User receiver,Long chatRoomNo){
        log.info("sendMessageToChatter");
        //만약 receiver가 로그인해서 sseEmitter를 발급 받았다면
        if(sseEmitterMap.containsKey(receiver.getNo())){
            SseEmitter sseEmitter = sseEmitterMap.get(receiver.getNo());

            //Json 객체 만든다.
            String message = new JSONObject()
                    .put("senderNickname", sender.getNickname())
                    .put("receiverNickName", receiver.getNickname())
                    .put("senderNo", sender.getNo())
                    .put("receiverNo", receiver.getNo())
                    .put("chatRoomNo", chatRoomNo).toString();
            log.info("message = {}", message);
            try {
                //메시지 보낸다.
                sseEmitter.send(SseEmitter.event().name("new chat").data(message));
                log.info("message sent");
            } catch (IOException e) {
                sseEmitterMap.remove(receiver.getNo());
                throw new CustomException(CustomExceptionStatus.SSEEMITTER_DOES_NOT_EXIST);
            }
        }
    }
    //Test Method
    public void sendMessageTest(){
        log.info("sseEmitterMap.size() = {}", sseEmitterMap.size());
        String sendingMessage = new JSONObject()
                .put("auctionNo",1)
                .put("bidderNickname", 1)
                .put("price", 1)
                .toString();
        for (Long userNo : sseEmitterMap.keySet()) {
            try {
                sseEmitterMap.get(userNo).send(SseEmitter.event().name("new bid").data(sendingMessage));
            } catch (IOException e) {
                sseEmitterMap.remove(userNo);
                throw new CustomException(CustomExceptionStatus.SSEEMITTER_DOES_NOT_EXIST);
            }
        }
    }
}
