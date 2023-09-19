package com.ssafy.ssagri.domain.usedproductphoto.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductPhotoRequest {
    private Long photoNo;
    private String link;

    @Builder
    public UsedProductPhotoRequest(Long photoNo, String link) {
        this.photoNo = photoNo;
        this.link = link;
    }
}
