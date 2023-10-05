package com.ssafy.ssagri.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileDTO {
    MultipartFile multipartFile;
    String fileFolder;
}
