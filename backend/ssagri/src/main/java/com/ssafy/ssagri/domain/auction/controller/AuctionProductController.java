package com.ssafy.ssagri.domain.auction.controller;

import com.ssafy.ssagri.domain.S3.S3Service;
import com.ssafy.ssagri.domain.auction.service.AuctionProductService;
import com.ssafy.ssagri.domain.auction.dto.AuctionProductAllDTO;
import com.ssafy.ssagri.domain.auction.dto.AuctionProductCreateDTO;

import com.ssafy.ssagri.dto.etc.CustomResponseBody;
import com.ssafy.ssagri.entity.auction.AuctionProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/auction-product")
@RequiredArgsConstructor
@Api(tags = {"경매에 대한 API"})
@CrossOrigin("*")
public class AuctionProductController {

    private final AuctionProductService auctionProductService;
    private final S3Service s3service;

    @GetMapping(value = "/all-list")
    @ApiOperation("모든 경매 출력")
    public List<AuctionProductAllDTO> allList(){
        System.out.println("들어왔니");
        return auctionProductService.getAuctionProducts();

    }


    @PostMapping(value = "/auction/regist")
    @ApiOperation("경매 등록")
    public Long auctionJoinList(@RequestBody AuctionProductCreateDTO auctionProductCreateDTO){
        System.out.println("dididi");
        return auctionProductService.setAuctionProduct(auctionProductCreateDTO);
        // response 나중에 처리
    }

    @PostMapping(value = "/upload/profile/{no}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation("경매 상품 추가시 사진 업로드")
    public void auctionImageUpload(@RequestParam("multipartFile") MultipartFile multipartFile, @PathVariable("no") Long no) throws IOException {

        System.out.println("업로드");
        auctionProductService.auctionImageUpload(multipartFile, "auction" + "/" + no , no); // lesson 가 lesson/로 들어감.
//        return auctionProductService.auctionImageUploadToAWS(multipartFile, "auction" + "/" + no , no);

    }


    // S3 경매 상품 이미지 불러오기
    @ApiOperation("경매 상품 사진 불러오기")
    @GetMapping("/load/{auctionProductNo}")
    public ResponseEntity<CustomResponseBody> profileLoad(@PathVariable Long auctionProductNo) {
        CustomResponseBody responseBody = new CustomResponseBody<>("등록된 사진이 있습니다.");
        try {
            responseBody.setResult(auctionProductService.auctionProductLoad(auctionProductNo));
        } catch (IllegalStateException i) {
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(i.getMessage());
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e) {
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.ok().body(responseBody);
        }
        return ResponseEntity.ok().body(responseBody);
    }

    // 옥션 상품 카테고리별 검색
    @ApiOperation("경매 상품 카테고리별 검색")
    @GetMapping("/type")
    public List<AuctionProductAllDTO> typeList(@RequestParam("auctionType")AuctionProductType type){
        return auctionProductService.auctionTypeList(type);

    }

    // 검색으로 상품 검색
    @ApiOperation("검색으로 상품 검색")
    @GetMapping("/search")
    public List<AuctionProductAllDTO> searchList(@RequestParam("auctionSearch") String searchWord){
        return auctionProductService.auctionSearchList(searchWord);

    }

    // 경매 상품 상세 페이지
    @ApiOperation("경매 상품 상세 페이지")
    @GetMapping("/detail")
    public AuctionProductAllDTO auctionDetail(@RequestParam("auctionNo") Long auctionNo){
        return auctionProductService.auctionDetail(auctionNo);

    }




}
