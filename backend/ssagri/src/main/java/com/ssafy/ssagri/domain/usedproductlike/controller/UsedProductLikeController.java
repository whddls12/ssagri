package com.ssafy.ssagri.domain.usedproductlike.controller;

import com.ssafy.ssagri.domain.usedproduct.service.UsedProductService;
import com.ssafy.ssagri.domain.usedproductlike.service.UsedProductLikeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("usedproductlike")
@Api(tags = {"[USEDPRODUCTLIKE] 중고상품 좋아요API"})
public class UsedProductLikeController {

    private final UsedProductLikeService usedProductLikeService;

    @PostMapping("/{userNo}/{usedProductNo}")
    @Operation(summary = "좋아요 등록하는 메서드")
    public ResponseEntity<Void> save(@PathVariable("userNo") final Long userNo, @PathVariable("usedProductNo") final Long usedProductNo) {
        usedProductLikeService.saveUsedProductLike(userNo,usedProductNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userNo}/{usedProductNo}")
    @Operation(summary = "좋아요 취소하는 메서드")
    public ResponseEntity<Void> delete(@PathVariable("userNo") Long userNo,@PathVariable("usedProductNo") Long usedProductNo) {
        usedProductLikeService.deleteUsedProductLike(userNo,usedProductNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
