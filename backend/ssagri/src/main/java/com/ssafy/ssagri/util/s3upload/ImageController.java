package com.ssafy.ssagri.util.s3upload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Getter
@RequestMapping("/api/image")
public class ImageController {
    //S3에 잘 올라가는지 테스트용 컨트롤러
}
