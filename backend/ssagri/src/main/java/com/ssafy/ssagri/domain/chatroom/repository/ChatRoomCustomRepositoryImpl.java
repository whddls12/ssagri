package com.ssafy.ssagri.domain.chatroom.repository;

import static com.ssafy.ssagri.entity.user.QUser.user;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssagri.domain.chatroom.dto.ChatRoomListResponseDto;
import com.ssafy.ssagri.entity.chat.ChatRoom;
import com.ssafy.ssagri.entity.chat.QChatRoom;

import com.ssafy.ssagri.entity.user.Region;
import com.ssafy.ssagri.entity.user.User;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ChatRoomCustomRepositoryImpl
        implements ChatRoomCustomRepository
{
    private final JPAQueryFactory jpaQueryFactory;

    QChatRoom chatRoom = QChatRoom.chatRoom;


    public ChatRoomCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    //userA,userB가 참여하는 채팅방 있는지 조회
    //Optional 반환
    @Override
    public Optional<ChatRoom> selectByUserAUserB(Long userANo, Long userBNo)
    {
        ChatRoom findChatRoom = jpaQueryFactory.selectFrom(chatRoom)
                .where(
                        chatRoom.userA.no.eq(userANo).and(chatRoom.userB.no.eq(userBNo))
                                .or(chatRoom.userA.no.eq(userBNo).and(chatRoom.userB.no.eq(userANo))))
                .fetchOne();
        return Optional.ofNullable(findChatRoom);
    }

    //유저 A가 참여하고 있는 모든 채팅방 select
    @Override
    public List<ChatRoom> selectByUserNo(Long no){
        List<ChatRoom> chatRoomList = jpaQueryFactory
                .selectFrom(chatRoom)
                .where(chatRoom.userA.no.eq(no)
                        .or(chatRoom.userB.no.eq(no))).fetch();
        return chatRoomList;
    }

    @Override
    public ChatRoomListResponseDto findReceiver(Long receiverNo) {
        List<Tuple> result = jpaQueryFactory
            .select(user.no, user.nickname, user.profile, user.region)
            .from(user)
            .where(user.no.eq(receiverNo))
            .fetch();

        Long no = null;
        String nickname = null;
        String profile = null;
        Region region = null;
        for (Tuple t : result) {
            no = t.get(user.no);
            nickname = t.get(user.nickname);
            profile = t.get(user.profile);
            region = t.get(user.region);
        }

        return ChatRoomListResponseDto.builder()
            .receiverNo(no)
            .receiverNickName(nickname)
            .receiverProfile(profile)
            .receiverRegion(region)
            .build();
    }

    @Override
    public String findNickname(Long userNo) {
        List<User> fetch = jpaQueryFactory.selectFrom(user)
            .where(user.no.eq(userNo)).fetch();
        String nickname = null;
        for (User u : fetch) {
            nickname = u.getNickname();
        }
        return nickname;
    }

}
