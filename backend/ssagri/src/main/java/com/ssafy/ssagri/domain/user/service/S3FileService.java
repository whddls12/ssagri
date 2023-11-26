package com.ssafy.ssagri.domain.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.FILE_UPLOAD_FAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileService {

    @Qualifier("s3")
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public String saveFile(HttpServletRequest request, MultipartFile multipartFile, String dirName) throws CustomException {
        String key = dirName + "/" + UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            amazonS3.putObject(bucket, key, multipartFile.getInputStream(), metadata); //파일을 저장해주는 메소드

            //DB에 해당 url을 저장

            return amazonS3.getUrl(bucket, key).toString(); //getURI를 통해 파일 저장 Url을 리턴
        } catch (Exception e) {
            log.error("[ERR] {}", e.getMessage());
            throw new CustomException(FILE_UPLOAD_FAIL);
        }
    }

}