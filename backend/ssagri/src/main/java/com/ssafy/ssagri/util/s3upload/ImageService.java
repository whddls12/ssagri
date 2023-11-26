package com.ssafy.ssagri.util.s3upload;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@NoArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    //임시 메서드
    public String saveImage(MultipartFile multipartFile) throws IOException {
        String imagePath = "";
        return imagePath;
    }
}
