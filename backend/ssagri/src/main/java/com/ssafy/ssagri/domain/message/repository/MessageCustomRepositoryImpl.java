package com.ssafy.ssagri.domain.message.repository;

import static com.ssafy.ssagri.entity.chat.QMessage.message;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssagri.entity.chat.Message;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Slf4j
public class MessageCustomRepositoryImpl implements MessageCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public MessageCustomRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Message> findMessagesByRoomNo(Long roomNo, Pageable pageable) {
        QueryResults<Message> queryResults = jpaQueryFactory.selectFrom(message)
            .where(message.roomNo.eq(roomNo))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(message.time.desc())
            .fetchResults();

        List<Message> results = queryResults.getResults();
        log.info("UsedProductRepository results: {}", results);
        long total = queryResults.getTotal();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public List<Message> findChatRoomByUserNo(Long userNo) {
        log.info("userNo : {}", userNo);
        return jpaQueryFactory
            .selectFrom(message)
            .where(message.id.in(
                JPAExpressions
                    .select(message.id.max())
                    .from(message)
                    .where(message.receiverNo.eq(userNo).or(message.senderNo.eq(userNo)))
                    .groupBy(message.roomNo)

            ))
            .orderBy(message.id.desc())
            .fetch();
    }
}
