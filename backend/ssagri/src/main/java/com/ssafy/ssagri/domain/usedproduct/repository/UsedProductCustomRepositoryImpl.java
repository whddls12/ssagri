package com.ssafy.ssagri.domain.usedproduct.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ssafy.ssagri.entity.usedproduct.ProductCategory;
import com.ssafy.ssagri.entity.usedproduct.QUsedProduct;
import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import com.ssafy.ssagri.entity.user.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UsedProductCustomRepositoryImpl implements UsedProductCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    QUsedProduct usedProduct = QUsedProduct.usedProduct;

    public UsedProductCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    /*
    * 카테고리,지역 설정해서 모든 중고물품 다 가져올 수 있는 메서드
    * */
    @Override
    public Page<UsedProduct> selectAllUsedProduct(ProductCategory productCategory, Region region,String search ,Pageable pageable) {

        log.info("UsedProductRepository category: {}, region: {}, search: {}", productCategory, region, search);
        log.info("UsedProductRepository pageable: {}", pageable);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(usedProduct.deleteDate.isNull());

        if (productCategory != null) {
            builder.and(usedProduct.category.eq(productCategory));
        }

        if(region != null){
            builder.and(usedProduct.region.eq(region));
        }

        if(search != null){
            builder.and(usedProduct.title.contains(search));
        }

        QueryResults<UsedProduct> usedProductQueryResults = jpaQueryFactory.selectFrom(usedProduct)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable).toArray(new OrderSpecifier[0]))
                .fetchResults();

        List<UsedProduct> results = usedProductQueryResults.getResults();
        log.info("UsedProductRepository results: {}", results);
        long total = usedProductQueryResults.getTotal();

        return new PageImpl<>(results, pageable, total);
    }
    /**
     * 특정 유저가 등록한 중고물품 다 가져오는 메서드
     */
    @Override
    public Page<UsedProduct> selectUsedProductByUserNo(Long userNo, Pageable pageable) {
        QueryResults<UsedProduct> usedProductQueryResults = jpaQueryFactory.selectFrom(usedProduct)
                .where(usedProduct.deleteDate.isNull().and(usedProduct.user.no.eq(userNo)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable).toArray(new OrderSpecifier[0]))
                .fetchResults();
        List<UsedProduct> results = usedProductQueryResults.getResults();
        long total = usedProductQueryResults.getTotal();

        return new PageImpl<>(results, pageable, total);
    }


    private List<OrderSpecifier<?>> getOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> specifiers = new ArrayList<>();

        if (pageable != null && pageable.getSort().isSorted()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "no":
                        specifiers.add(new OrderSpecifier<>(direction, usedProduct.no));
                        break;
                    case "price":
                        specifiers.add(new OrderSpecifier<>(direction, usedProduct.price));
                        break;
                    case "like":
                        specifiers.add(new OrderSpecifier<>(direction, usedProduct.likeCount));
                        break;
                    // 추가적인 필드에 대한 정렬 조건을 여기에 추가
                }
            }
        }
        // 항상 마지막에 'no'를 기준으로 내림차순 정렬 조건을 추가
        specifiers.add(new OrderSpecifier<>(Order.DESC, usedProduct.no));
        return specifiers;
    }

}

