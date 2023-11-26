package com.ssafy.ssagri.domain.chatroom.service;

import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomDetailResponseDto;
import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomListResponseDto;
import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomResponseDto;
import com.ssafy.ssagri.domain.chatroom.repository.ChatRoomRepository;
import com.ssafy.ssagri.domain.message.dto.MessageResponseDto;
import com.ssafy.ssagri.domain.message.repository.MessageRepository;
import com.ssafy.ssagri.domain.message.service.MessageService;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.entity.chat.ChatRoom;
import com.ssafy.ssagri.entity.chat.Message;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import java.util.LinkedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.CHATROOM_DOES_NOT_EXIST;
import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.USER_DOES_NOT_EXSIST;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {
    private final UserRegistAndModifyRepository userRegistAndModifyRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;
    private final MessageService messageService;

    /*
    * 특정유저의 No를 받아서 그 사람 참여하고
    * 있는 모든 채팅방 정보 Dto로 반환하는 메서드
    * */
    public List<ChatRoomListResponseDto> selectAllChatRoomByUser(Long userNo, Long sellorNo){
        // 나와 관련된 채팅방들의 데이터를 가져온다. (메세지는 안 가져옴)
        List<Message> messageList = messageRepository.findChatRoomByUserNo(userNo);
        log.info("MessageList {} : ", messageList);
        // 반환할 변수명 미리 지정
        List<ChatRoomListResponseDto> chatRoomResponseDtoList = new LinkedList<>();

        // 내가 호출하는 sellor와 채팅하는 채팅방이 가장 위로 올라오도록 설정
        // 1. 내가 다른 사람들과 채팅한 메세지가 하나도 없을 때
        //      -> 채팅방은 만들어졌다. 그 채팅방을 가져와 반환하지만 메세지와 메세지 시간 데이터는 없을 것이다.
        if (messageList == null || messageList.size() == 0) {
            // sellorNo의 닉네임, 프로필 링크, 지역 가져온다.
            ChatRoomListResponseDto receiver = chatRoomRepository.findReceiver(sellorNo);
            // 나와 상대방이 대화한 채팅방 관련 정보를 가져온다.
            Optional<ChatRoom> findChatRoom = chatRoomRepository.selectByUserAUserB(userNo, sellorNo);
            //채팅방이 존재한다면 저장 = 무조건 저장될 것이다.
            Long chatRoomNo = null;
            if (findChatRoom.isPresent()) {
                chatRoomNo = findChatRoom.get().getNo();
            }
            // 메세지가 없더라도, 채팅방의 정보는 가져온다.
            ChatRoomListResponseDto responseDto = ChatRoomListResponseDto.builder()
                .receiverNo(receiver.getReceiverNo())
                .chatRoomNo(chatRoomNo)
                .lastMent("")
                .lastDate(null)
                .receiverNickName(receiver.getReceiverNickName())
                .receiverProfile(receiver.getReceiverProfile())
                .receiverRegion(receiver.getReceiverRegion())
                .build();
            chatRoomResponseDtoList.add(responseDto);
            log.info("chatRoomResponseDtoList : {}", chatRoomResponseDtoList);
            return chatRoomResponseDtoList;
        }

        // 여기부터는 messageList가 무조건 있는 상황이다.

        // 마지막으로 메세지를 나눈 상대방의 No를 가져온다.
        Long firstReceiverNo = null;
        if (userNo.equals(messageList.get(0).getSenderNo())) {
            firstReceiverNo = messageList.get(0).getReceiverNo();
        } else {
            firstReceiverNo = messageList.get(0).getSenderNo();
        }

        // 2. 채팅한 메세지가 있지만, 마지막에 대화한 사람이 sellorNo가 아닐 때
        //      -> sellorNo와의 채팅방을 맨 처음에 가져오고, 이후에 sellorNo를 제외한 나머지 채팅 목록을 가져온다.
        if (!firstReceiverNo.equals(sellorNo)) {
            boolean chatExist = false;

            for (Message msg : messageList) {
                Long receiverNo = null;
                // 사용자와 수신인의 no가 같다면, 반대편 사용자는 receiverNo, 아니라면 반대
                if (userNo.longValue() == msg.getSenderNo().longValue()) {
                    receiverNo = msg.getReceiverNo();
                } else {
                    receiverNo = msg.getSenderNo();
                }
                ChatRoomListResponseDto receiver = chatRoomRepository.findReceiver(receiverNo);
                ChatRoomListResponseDto responseDto = ChatRoomListResponseDto.builder()
                    .receiverNo(receiver.getReceiverNo())
                    .chatRoomNo(msg.getRoomNo())
                    .lastMent(msg.getContent())
                    .lastDate(msg.getTime())
                    .receiverNickName(receiver.getReceiverNickName())
                    .receiverProfile(receiver.getReceiverProfile())
                    .receiverRegion(receiver.getReceiverRegion())
                    .build();

                if (receiverNo.equals(sellorNo)) {
                    chatExist = true;
                    chatRoomResponseDtoList.add(0, responseDto);
                } else {
                    chatRoomResponseDtoList.add(responseDto);
                }
            }

            // 2-2. 채팅한 메세지가 있지만, 마지막에 대화한 사람이 sellorNo가 아니고, 내가 대화하려는 사람과 대화한 적은 없을 때
            if(!chatExist) {
                // sellorNo의 닉네임, 프로필 링크, 지역 가져온다.
                ChatRoomListResponseDto receiver = chatRoomRepository.findReceiver(sellorNo);
                // 나와 상대방이 대화한 채팅방 관련 정보를 가져온다.
                Optional<ChatRoom> findChatRoom = chatRoomRepository.selectByUserAUserB(userNo, sellorNo);
                //채팅방이 존재한다면 저장 = 무조건 저장될 것이다.
                Long chatRoomNo = null;
                if (findChatRoom.isPresent()) {
                    chatRoomNo = findChatRoom.get().getNo();
                }
                ChatRoomListResponseDto responseDto = ChatRoomListResponseDto.builder()
                    .receiverNo(receiver.getReceiverNo())
                    .chatRoomNo(chatRoomNo)
                    .lastMent("")
                    .lastDate(null)
                    .receiverNickName(receiver.getReceiverNickName())
                    .receiverProfile(receiver.getReceiverProfile())
                    .receiverRegion(receiver.getReceiverRegion())
                    .build();
                chatRoomResponseDtoList.add(0, responseDto);
            }

            log.info("chatRoomResponseDtoList : {}", chatRoomResponseDtoList);
            return chatRoomResponseDtoList;
        } else {    // 3. 채팅한 메세지가 있고, 마지막에 대화한 사람이 sellorNo가 맞을 때 -> 그냥 가져오면 된다.
            for (Message msg : messageList) {
                Long receiverNo = null;
                // 사용자와 수신인의 no가 같다면, 반대편 사용자는 receiverNo, 아니라면 반대
                if (userNo.longValue() == msg.getSenderNo().longValue()) {
                    receiverNo = msg.getReceiverNo();
                } else {
                    receiverNo = msg.getSenderNo();
                }

                ChatRoomListResponseDto receiver = chatRoomRepository.findReceiver(receiverNo);
                ChatRoomListResponseDto responseDto = ChatRoomListResponseDto.builder()
                    .receiverNo(receiver.getReceiverNo())
                    .chatRoomNo(msg.getRoomNo())
                    .lastMent(msg.getContent())
                    .lastDate(msg.getTime())
                    .receiverNickName(receiver.getReceiverNickName())
                    .receiverProfile(receiver.getReceiverProfile())
                    .receiverRegion(receiver.getReceiverRegion())
                    .build();

                chatRoomResponseDtoList.add(responseDto);
            }
            log.info("chatRoomResponseDtoList : {}", chatRoomResponseDtoList);
            return chatRoomResponseDtoList;
        }
    }

    public ChatRoomResponseDto selectOneChatRoom(Long chatRoomNo){
        Optional<ChatRoom> findChatRoom = chatRoomRepository.findById(chatRoomNo);
        if (findChatRoom.isEmpty()){
            //예외처리
            throw new CustomException(CHATROOM_DOES_NOT_EXIST);
        }else{
            return findChatRoom.get().toResponse();
        }
    }

    //이 메서드 실행하기 전에는 save 먼저 해서 채팅방을 만들어 줘야 한다.
    @Transactional
    public ChatRoomDetailResponseDto selectChatRoomDetailByUsers(Long userA , Long userB, Pageable pageable){
        //채팅방이 없으면 생성하고 있으면 조회하는 메서드
        ChatRoomResponseDto chatRoomResponseDto = saveChatRoom(userA, userB);

        Page<MessageResponseDto> messageResponseList = messageService.selectMessageResponse(chatRoomResponseDto.getNo(), pageable);
        log.info("Service - Page<MessageResponseDto> : {}", messageResponseList);


        ChatRoomDetailResponseDto chatRoomDetailResponseDto = ChatRoomDetailResponseDto.builder()
                .chatRoomNo(chatRoomResponseDto.getNo())
                .userANo(userA)
                .userBNo(userB)
                .chatRoomCode(chatRoomResponseDto.getRoomCode())
                .messageResponseList(messageResponseList)
                .build();

        log.info("Service - ChatRoomDetailResponseDto : {}", chatRoomDetailResponseDto);

        return chatRoomDetailResponseDto;
    }
    /*
    * 채팅방의 최근 대화시간으로
    * 업데이트 하는 메서드
    * */
    @Transactional
    public void updateDate(Long chatRommNo){
        Optional<ChatRoom> findChatRoom = chatRoomRepository.findById(chatRommNo);

        //채팅방이 존재한다면
        if (findChatRoom.isPresent()) {
            findChatRoom.get().updateDate();
        }
        //채팅방이 존재하지 않으면
        else {
            //예외처리 할것
            throw new CustomException(CHATROOM_DOES_NOT_EXIST);
        }
    }


    /*
    * 채팅방 지우는 메서드
    * */
    @Transactional
    public void deleteChatRoom(Long userANo, Long userBNo){
        Optional<ChatRoom> findChatRoom = chatRoomRepository.selectByUserAUserB(userANo, userBNo);
        //채팅방이 존재한다면
        if (findChatRoom.isPresent()) {
            Long chatRoomNo = findChatRoom.get().getNo();
            chatRoomRepository.deleteById(chatRoomNo);
        }
        //채팅방이 존재하지 않으면
        else {
            //예외처리 할것
            throw new CustomException(CHATROOM_DOES_NOT_EXIST);
        }
    }


    /*유저 두 명 받아서
     채팅방 만드는 메서드,
     */
    @Transactional
    public ChatRoomResponseDto saveChatRoom(Long userANo, Long userBNo){
        //채팅방 이미 있는지 확인하고 없으면 만들고 반환
        Optional<ChatRoom> findChatRoom = chatRoomRepository.selectByUserAUserB(userANo, userBNo);

        //채팅방 있으면 그대로 반환
        if (findChatRoom.isPresent()) {
            return findChatRoom.get().toResponse();
        }

        //채팅방 없으면 생성 후 반환
        Optional<User> findUserA = userRegistAndModifyRepository.findById(userANo);
        Optional<User> findUserB = userRegistAndModifyRepository.findById(userBNo);

        User userA = null;
        User userB = null;

        if (findUserA.isPresent()) {
            userA = findUserA.get();
        }else{
            throw new CustomException(USER_DOES_NOT_EXSIST);
        }
        if (findUserB.isPresent()) {
            userB = findUserB.get();
        }
        else{
            throw new CustomException(USER_DOES_NOT_EXSIST);
        }

        //채팅방 생성한다.
        String roomCode = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .userA(userA)
                .userB(userB)
                .roomCode(roomCode).build();
        chatRoomRepository.save(chatRoom);
        return chatRoom.toResponse();
    }

    public String selectNickname(Long userNo) {
        return chatRoomRepository.findNickname(userNo);
    }
}
