package com.ssafy.ssagri.domain.usedproductlike.service;

import com.ssafy.ssagri.domain.usedproduct.repository.UsedProductRepository;
import com.ssafy.ssagri.domain.usedproductlike.repository.UsedProductLikeRepository;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import com.ssafy.ssagri.entity.usedproduct.UsedProductLike;
import com.ssafy.ssagri.entity.user.User;
import com.ssafy.ssagri.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsedProductLikeService {

    private final UsedProductLikeRepository usedProductLikeRepository;
    private final UserRegistAndModifyRepository userRegistRepository;
    private final UsedProductRepository usedProductRepository;


    /*
    * 1. 이미 좋아요가 존재하는지 조회
    * 2. 유저가 존재하는지 조회
    * 3. 중고상품이 존재하는지 조회
    * 4. 상품 좋아요 DB에 저장
    * 5. 중고상품 좋아요 카운트 업데이트
    * */
    @Transactional
    public void saveUsedProductLike(Long userNo, Long usedProductNo) {
        //이미 좋아요를 눌렀는지 체크
        if(usedProductLikeRepository.checkLikeByUserNo(userNo, usedProductNo)){
           throw new CustomException(USED_PRODUCT_LIKE_ALREADY_EXIST);
        }
        //유저 조회
        Optional<User> findUser = userRegistRepository.findById(userNo);
        User user = null;
        if (findUser.isPresent()) {
            user = findUser.get();
        } else {
            throw new CustomException(USER_DOES_NOT_EXSIST);
        }
        //중고 상품 조회
        Optional<UsedProduct> findUsedProduct = usedProductRepository.findById(usedProductNo);
        UsedProduct usedProduct = null;
        if (findUsedProduct.isPresent()) {
            usedProduct = findUsedProduct.get();
        } else {
            throw new CustomException(USED_PRODUCT_DOES_NOT_EXIST);
        }
        //좋아요 Entity 생성
        UsedProductLike usedProductLike = UsedProductLike.builder()
                .user(user)
                .usedProduct(usedProduct)
                .build();
        //좋아요 DB 저장
        usedProductLikeRepository.save(usedProductLike);

        //상품 좋아요 업데이트
        int likeCount = usedProduct.getLikeCount();
        usedProduct.setLikeCount(likeCount + 1);
    }

    /*
    * 1. 유저가 상품 좋아요 눌렀는지 확인
    * 2. 상품좋아요 테이블에서 삭제
    * 3. 상품에서 좋아요 하나 내려주기
    * */
    @Transactional
    public void deleteUsedProductLike(Long userNo, Long usedProductNo) {
        Optional<UsedProductLike> findUsedProductLike = usedProductLikeRepository.findLikeByUserNoAndUsedProductNo(userNo, usedProductNo);
        if (findUsedProductLike.isPresent()){
            usedProductLikeRepository.delete(findUsedProductLike.get());
        }else{
            throw new CustomException(USED_PRODUCT_LIKE_DOES_NOT_EXIST);
        }


        Optional<UsedProduct> findUsedProduct = usedProductRepository.findById(usedProductNo);
        UsedProduct usedProduct = null;
        if (findUsedProduct.isPresent()) {
            usedProduct = findUsedProduct.get();
        } else {
            throw new CustomException(USED_PRODUCT_DOES_NOT_EXIST);
        }

        int likeCount = usedProduct.getLikeCount();
        usedProduct.setLikeCount(likeCount - 1);
    }
}
