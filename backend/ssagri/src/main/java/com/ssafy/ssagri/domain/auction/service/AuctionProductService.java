package com.ssafy.ssagri.domain.auction.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.ssafy.ssagri.domain.S3.S3Service;
import com.ssafy.ssagri.domain.auction.dto.Images;
import com.ssafy.ssagri.domain.auction.repository.AuctionPhotoRepository;
import com.ssafy.ssagri.domain.auction.repository.AuctionRepository;
import com.ssafy.ssagri.domain.user.repository.UserRegistAndModifyRepository;
import com.ssafy.ssagri.domain.auction.dto.AuctionProductAllDTO;
import com.ssafy.ssagri.domain.auction.dto.AuctionProductCreateDTO;
import com.ssafy.ssagri.entity.auction.AuctionProduct;
import com.ssafy.ssagri.entity.auction.AuctionProductImage;
import com.ssafy.ssagri.entity.auction.AuctionProductType;
import com.ssafy.ssagri.entity.auction.AuctionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuctionProductService {

    private final S3Service s3Service;
    private final AuctionRepository auctionRepository;
    private final UserRegistAndModifyRepository userRegistRepository;
    private final AuctionPhotoRepository auctionPhotoRepository;


    // 경매 모든 리스트 출력
    public List<AuctionProductAllDTO> getAuctionProducts() {


         List<AuctionProduct> products = auctionRepository.findAllByOrderByEndDateDesc();


         List<AuctionProductAllDTO> result = new ArrayList<>();


         for(int i=0;i<products.size();i++){

         String startDate = products.get(i).getStartDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

         String endDate = products.get(i).getEndDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

             AuctionProductAllDTO auctionProductAllDTO = AuctionProductAllDTO.builder()
                     .no(products.get(i).getNo())
                     .userNickName(products.get(i).getUser().getNickname())
                     .userNo(products.get(i).getUser().getNo())
                     .number(products.get(i).getUser().getNumber())
                     .region(products.get(i).getUser().getRegion())
                     .profile(products.get(i).getUser().getProfile())
                     .name(products.get(i).getName())
                     .upPrice(products.get(i).getUpPrice())
                     .downPrice(products.get(i).getDownPrice())
                     .priceCount(products.get(i).getPriceCount())
                     .startDate(startDate)
                     .endDate(endDate)
                     .comment(products.get(i).getComment())
                     .auctionStatus(products.get(i).getAuctionStatus())
                     .finallyPrice(products.get(i).getFinallyPrice())
                     .createDate(products.get(i).getCreateDate())
                     .type(products.get(i).getType())
                     .originPrice(products.get(i).getOriginPrice())
                     .build();

                result.add(auctionProductAllDTO);

         }

         return result;
    }


    // 경매 상품 등록
    @Transactional
    public Long setAuctionProduct(AuctionProductCreateDTO auctionProductCreateDTO){

        String startDate1 = auctionProductCreateDTO.getStartDate();

        String endDate1 = auctionProductCreateDTO.getEndDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");

        LocalDateTime startDate = LocalDateTime.parse(startDate1, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDate1, formatter);

        System.out.println(startDate);
        System.out.println(endDate);



        AuctionProduct auctionProduct = AuctionProduct.builder()
                .user(userRegistRepository.findByNo(auctionProductCreateDTO.getUserNo()))
                .name(auctionProductCreateDTO.getName())
//                .upPrice(auctionProductCreate.getUpPrice())
                .downPrice(auctionProductCreateDTO.getDownPrice())
                .priceCount(auctionProductCreateDTO.getCountPrice())
                .startDate(startDate)
                .auctionStatus(AuctionStatus.예정)
                .endDate(endDate)
                .comment(auctionProductCreateDTO.getComment())
                .createTime(LocalDateTime.now())
                .originPrice(auctionProductCreateDTO.getOriginPrice())
                .type(auctionProductCreateDTO.getType()).build();


            AuctionProduct auctionProduct1 = auctionRepository.save(auctionProduct);

            return auctionProduct1.getNo();
    }

    // 옥션 상품 이미지 업로드
    @Transactional
    public void auctionImageUpload(MultipartFile file, String dirName, Long no){

       String key =  s3Service.S3ImageUploadToAWS(file, dirName, no);

        System.out.println(key);

        AuctionProduct auctionProduct = auctionRepository.findByNo(no);

        AuctionProductImage auctionProductImage = AuctionProductImage.builder()
                .auctionProductNo(auctionProduct)
                .imageLink(key).build();

        auctionPhotoRepository.save(auctionProductImage);
    }




    // 경매 상품 이미지 업로드
    public List<Images> auctionProductLoad(Long auctionProductNo) {

        List<AuctionProductImage> list = auctionPhotoRepository.findByAuctionProductNo(auctionRepository.findByNo(auctionProductNo));

        if (list.size() == 0) {
            throw new IllegalStateException("등록된 사진이 없습니다.");

        }
        List<Images> images = new ArrayList<>();

        for(int i=0;i<list.size();i++){

            Images image = Images.builder()
                    .no(list.get(i).getNo())
                    .auctionProductNo(list.get(i).getAuctionProductNo().getNo())
                    .imageLink(list.get(i).getImageLink()).build();

            images.add(image);
        }

        return images;


    }

    // 경매 상품 상세페이지
//    public AuctionProductAllDTO auctionDetail(Long auctionProductNo){
//       AuctionProduct auctionProduct = auctionRepository.findByNo(auctionProductNo);
//
//        String startDate = auctionProduct.getStartDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
//
//        String endDate = auctionProduct.getEndDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
//
//       AuctionProductAllDTO auctionProductAllDTO =AuctionProductAllDTO.builder()
//               .no(auctionProduct.getNo())
//               .userNickName(userRegistRepository.findByNo(auctionProduct.getUser().getNo()).getNickname())
//               .name(auctionProduct.getName())
//               .upPrice(auctionProduct.getUpPrice())
//               .downPrice(auctionProduct.getDownPrice())
//               .priceCount(auctionProduct.getPriceCount())
//               .startDate(startDate) // String 으로 변환해서 주기
//               .endDate(endDate)
//               .comment(auctionProduct.getComment())
//               .auctionStatus(auctionProduct.getAuctionStatus())
//               .finallyPrice(auctionProduct.getFinallyPrice())
//               .deleteDate(auctionProduct.getDeleteDate())
//               .createDate(auctionProduct.getCreateDate())
//               .type(auctionProduct.getType())
//               .originPrice(auctionProduct.getOriginPrice()).build();
//
//       return auctionProductAllDTO;
//    }


    // 타입에 대한 경매 상품 리스트 출력
    public List<AuctionProductAllDTO> auctionTypeList(AuctionProductType type) {


        List<AuctionProduct> products = auctionRepository.findAllByType(type);


        List<AuctionProductAllDTO> result = new ArrayList<>();


        for(int i=0;i<products.size();i++){

            String startDate = products.get(i).getStartDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            String endDate = products.get(i).getEndDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            AuctionProductAllDTO auctionProductAllDTO = AuctionProductAllDTO.builder()
                    .no(products.get(i).getNo())
                    .userNickName(products.get(i).getUser().getNickname())
                    .userNo(products.get(i).getUser().getNo())
                    .number(products.get(i).getUser().getNumber())
                    .region(products.get(i).getUser().getRegion())
                    .profile(products.get(i).getUser().getProfile())
                    .name(products.get(i).getName())
                    .upPrice(products.get(i).getUpPrice())
                    .downPrice(products.get(i).getDownPrice())
                    .priceCount(products.get(i).getPriceCount())
                    .startDate(startDate)
                    .endDate(endDate)
                    .comment(products.get(i).getComment())
                     .auctionStatus(products.get(i).getAuctionStatus())
                    .finallyPrice(products.get(i).getFinallyPrice())
                    .createDate(products.get(i).getCreateDate())
                    .type(products.get(i).getType())
                    .originPrice(products.get(i).getOriginPrice())
                    .build();

            result.add(auctionProductAllDTO);

        }

        return result;
    }

    // 검색으로 경매 상품 검색
    public List<AuctionProductAllDTO> auctionSearchList(String searchWord) {


        List<AuctionProduct> products = auctionRepository.findAllByName(searchWord);


        List<AuctionProductAllDTO> result = new ArrayList<>();


        for(int i=0;i<products.size();i++){

            String startDate = products.get(i).getStartDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            String endDate = products.get(i).getEndDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            AuctionProductAllDTO auctionProductAllDTO = AuctionProductAllDTO.builder()
                    .no(products.get(i).getNo())
                    .userNickName(products.get(i).getUser().getNickname())
                    .userNo(products.get(i).getUser().getNo())
                    .number(products.get(i).getUser().getNumber())
                    .region(products.get(i).getUser().getRegion())
                    .profile(products.get(i).getUser().getProfile())
                    .name(products.get(i).getName())
                    .upPrice(products.get(i).getUpPrice())
                    .downPrice(products.get(i).getDownPrice())
                    .priceCount(products.get(i).getPriceCount())
                    .startDate(startDate)
                    .endDate(endDate)
                    .comment(products.get(i).getComment())
                     .auctionStatus(products.get(i).getAuctionStatus())
                    .finallyPrice(products.get(i).getFinallyPrice())
                    .createDate(products.get(i).getCreateDate())
                    .type(products.get(i).getType())
                    .originPrice(products.get(i).getOriginPrice())
                    .build();

            result.add(auctionProductAllDTO);

        }

        return result;
    }

    // 경매 상세 페이지
    public AuctionProductAllDTO auctionDetail(Long auctionNo) {

        AuctionProduct auctionProduct = auctionRepository.findByNo(auctionNo);


            String startDate = auctionProduct.getStartDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            String endDate = auctionProduct.getEndDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

            AuctionProductAllDTO auctionProductAllDTO = AuctionProductAllDTO.builder()
                    .no(auctionProduct.getNo())
                    .userNickName(auctionProduct.getUser().getNickname())
                    .userNo(auctionProduct.getUser().getNo())
                    .number(auctionProduct.getUser().getNumber())
                    .region(auctionProduct.getUser().getRegion())
                    .profile(auctionProduct.getUser().getProfile())
                    .name(auctionProduct.getName())
                    .upPrice(auctionProduct.getUpPrice())
                    .downPrice(auctionProduct.getDownPrice())
                    .priceCount(auctionProduct.getPriceCount())
                    .startDate(startDate)
                    .endDate(endDate)
                    .comment(auctionProduct.getComment())
                    .auctionStatus(auctionProduct.getAuctionStatus())
                    .finallyPrice(auctionProduct.getFinallyPrice())
                    .createDate(auctionProduct.getCreateDate())
                    .type(auctionProduct.getType())
                    .originPrice(auctionProduct.getOriginPrice())
                    .build();

            return auctionProductAllDTO;

    }



}
