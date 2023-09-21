package com.ssafy.ssagri.domain.usedproductphoto.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductPhotoResponseDto {
    private Long photoNo;
    private String link;


    @QueryProjection
    public UsedProductPhotoResponseDto(Long photoNo, String link) {
        this.photoNo = photoNo;
        this.link = link;
    }
}
