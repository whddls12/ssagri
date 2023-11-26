package com.ssafy.ssagri.domain.message.service;

import com.ssafy.ssagri.domain.message.dto.MessageRequestDto;
import com.ssafy.ssagri.domain.message.dto.MessageResponseDto;
import com.ssafy.ssagri.domain.message.repository.MessageRepository;
import com.ssafy.ssagri.domain.notification.NotificationService;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.entity.chat.Message;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRegistAndModifyRepository userRegistAndModifyRepository;
    private final NotificationService notificationService;

    public Page<MessageResponseDto> selectMessageResponse(Long roomNo, Pageable pageable){
        log.info("roomNo : {}",roomNo);

        //메시지 페이징으로 가져오기
        Page<Message> messages = messageRepository.findMessagesByRoomNo(roomNo, pageable);

        //메시지 content 저장
        List<Message> messageList = messages.getContent();

        //메시지 response list 선언
        List<MessageResponseDto> messageResponseList = new ArrayList<>();

        //메시지Entity 메시지ResponseDto로 변환
        for (Message message : messageList) {
            messageResponseList.add(message.toResponse());
        }

        //반환
        return new PageImpl<>(messageResponseList, messages.getPageable(), messages.getTotalElements());
    }



    /*
     * MessageRequest를 받아서
     * Entity로 바꾸고 DB 저장
     * MessageResponse로 바꿔서 client로 반환
     * */
    @Transactional
    public MessageResponseDto saveMessage(MessageRequestDto messageRequest){
        log.info("messageRequest = {}", messageRequest);

        //sender
        User sender = userRegistAndModifyRepository.findById(messageRequest.getSenderNo())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_DOES_NOT_EXSIST));
        log.info("sender = {}", sender);

        //receiver
        User receiver = userRegistAndModifyRepository.findById(messageRequest.getReceiverNo())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_DOES_NOT_EXSIST));
        log.info("receiver = {}", receiver);

        //Entity로 바꾸고
        Message message = messageRequest.toEntity(sender.getNickname(),receiver.getNickname());
        log.info("message = {}", message);

        //저장
        messageRepository.save(message);

        MessageResponseDto responseDto = message.toResponse();
        log.info("responseDto = {}", responseDto);



        //알림 보낸다.
        notificationService.sendMessageToChatter(sender,receiver,messageRequest.getChatRoomNo());


        //client로 반환
        return responseDto;
    }
}
