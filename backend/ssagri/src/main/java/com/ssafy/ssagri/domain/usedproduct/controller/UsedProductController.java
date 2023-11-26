package com.ssafy.ssagri.domain.usedproduct.controller;

import com.ssafy.ssagri.domain.usedproduct.dto.request.UsedProductSaveRequestDto;
import com.ssafy.ssagri.domain.usedproduct.dto.response.UsedProductDetailResponseDto;
import com.ssafy.ssagri.domain.usedproduct.dto.response.UsedProductResponseDto;
import com.ssafy.ssagri.domain.usedproduct.service.UsedProductService;
import com.ssafy.ssagri.entity.usedproduct.ProductCategory;
import com.ssafy.ssagri.entity.usedproduct.SaleStatus;
import com.ssafy.ssagri.entity.user.Region;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usedproduct")
@CrossOrigin("*")
@Api(tags = {"[USEDPRODUCT] 중고상품에 대한 API"})
@Slf4j
public class UsedProductController {

    private final UsedProductService usedProductService;

    @PostMapping("")
    @Operation(summary = "for-mdata로 사진, UsedProductSaveRequest 넘겨줘야 함"
        , description = "{\n" +
        "    \"userNo\":1,\n" +
        "    \"productCategory\":\"READY\",\n" +
        "    \"title\":\"title123\",\n" +
        "    \"content\":\"content\",\n" +
        "    \"price\":1234,\n" +
        "    \"saleStatus\":\"READY\"\n" +
        "} 형식으로 보내야 함")
    public ResponseEntity<Long> save(@RequestPart UsedProductSaveRequestDto usedProductSaveRequest

        ,@RequestPart("s3uploadMain") MultipartFile multipartFileMain)throws Exception {


        log.info("usedProductSaveRequest : {} ", usedProductSaveRequest);

        Long id = usedProductService.saveUsedProduct(usedProductSaveRequest,multipartFileMain);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }


    @GetMapping("/{userNo}")
    @ApiOperation("중고물품 리스트 조회하는 메서드 파라미터 값으로 userNo를 넣어주면 좋아요 상태도 같이 조회")
    @Operation(summary = "중고 물품 조회하는 메서드"
            ,description = "category, region, size, page 넘겨주기,\n" +
            " 정렬은 sort=like,asc 같은 식으로 넘긴다.(like 자리에 올 수 있는 것 : like,no,price, asc 자리에 올 수 있는 것 : asc, desc)\n" +
            " ex) http://localhost:5000/api/usedproduct/1?sort=like,desc&page=0&size=3&search=title1")
    public ResponseEntity<Page<UsedProductResponseDto>> selectList(@PathVariable(name = "userNo")Long userNo
            , @RequestParam(name = "category", required = false) ProductCategory productCategory
            , @RequestParam(name = "region", required = false) Region region
            , @RequestParam(name = "search", required = false) String search
            , Pageable pageable
            ) {

        log.info("controller userNo: {}, category: {}, region: {}, search: {}", userNo, productCategory ,region ,search);
        log.info("controller pageable: {}", pageable);

        pageable.getSort().get().forEach(sort -> {
            log.info("sort.getProperty() = {}", sort.getProperty());
            log.info("sort.getDirection() = {}", sort.getDirection());
        });
        Page<UsedProductResponseDto> usedProductResponseDtos = usedProductService.selectUsedProductList(userNo, productCategory, region, search, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(usedProductResponseDtos);
    }

    @GetMapping("/detail/{userNo}")
    @Operation(summary = "중고물품 detail넘겨주는 메서드, userNo, usedProductNo 넘겨줘야 함"
            ,description = "userNo, usedProductNo를 넘겨주면 중고물품 상세페이지에 필요한 정보 넘겨줌")
    public ResponseEntity<UsedProductDetailResponseDto> selectDetail(@PathVariable(name = "userNo")Long userNo,@RequestParam(name = "usedProductNo")Long usedProductNo) {
        UsedProductDetailResponseDto usedProductDetailResponseDto = usedProductService.selectUsedProductDetail(userNo,usedProductNo);
        return ResponseEntity.status(HttpStatus.OK).body(usedProductDetailResponseDto);
    }
    @GetMapping("/user/{userNo}")
    @Operation(summary = "특정 유저가 올린 상품리스트 가져오는 메서드")
    public ResponseEntity<Page<UsedProductResponseDto>> selectListByUser(@PathVariable(name = "userNo")Long userNo, Pageable pageable) {
        Page<UsedProductResponseDto> usedProductResponseDtos = usedProductService.selectUsedProductListByUser(userNo, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(usedProductResponseDtos);
    }

    @DeleteMapping("/{usedProductNo}")
    @Operation(summary = "삭제하는 메서드")
    public ResponseEntity<Long> delete(@PathVariable(name = "usedProductNo")Long usedProductNo){
        Long id = usedProductService.deleteUsedProduct(usedProductNo);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
