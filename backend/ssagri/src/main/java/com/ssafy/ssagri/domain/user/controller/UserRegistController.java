package com.ssafy.ssagri.domain.user.controller;

import com.ssafy.ssagri.domain.user.service.S3FileService;
import com.ssafy.ssagri.domain.user.service.UserRegistService;
import com.ssafy.ssagri.dto.user.ResponseDTO;
import com.ssafy.ssagri.dto.user.UserPasswordChangeDTO;
import com.ssafy.ssagri.dto.user.UserRegistDTO;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.jwt.JwtUtil;
import com.ssafy.ssagri.util.mail.EmailService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.ssafy.ssagri.util.ResponseStatusEnum.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/regist")
@Api(tags = "[USER]회원가입 및 이메일 인증 컨트롤러")
public class UserRegistController {

    private final UserRegistService userRegistService;
    private final EmailService emailService;
    private final S3FileService s3FileService;

    @Operation(summary = "회원가입 기능", description = "UserRegistDTO 입력값을 통해 회원 가입 및 등록 진행. \n 성공일 경우 REGISTER_IS_OK(1000, \"성공적으로 등록하였습니다.\")발생")
    @PostMapping("/")
    public ResponseEntity<?> registUser(@RequestBody UserRegistDTO userRegistDTO) throws Exception {
        return userRegistService.registUser(userRegistDTO);
    }

    @Operation(summary = "닉네임 중복 확인", description = "RequestParam(\"nickname\") 입력값을 통해 닉네임 중복 확인. \n" +
            "            \"중복일 경우 REGISTER_NICKNAME_IS_DUPLICATE(-1000, \\\"유저 닉네임이 중복됩니다.\\\") 발생(404)\\n\" +\n" +
            "            \"성공일 경우 REGISTER_NICKNAME_IS_OK(1001, \\\"닉네임이 유효합니다.\\\") 발생(200)\\n\" +\n" +
            "            \" ")
    @GetMapping("/check/nickname")
    public ResponseEntity<?> checkDuplicateNickname(@RequestParam("nickname")String nickname) throws CustomException {
        return userRegistService.checkDuplicateNickname(nickname);
    }

    @Operation(summary = "이메일 중복 확인", description = "RequestParam(\"email\") 입력값을 통해 닉네임 중복 확인.   \n" +
            "중복일 경우 REGISTER_EMAIL_IS_DUPLICATE(-1001, \"이메일이 중복됩니다.\") 발생\n" +
            "성공일 경우 REGISTER_EMAIL_IS_OK(1002, \"이메일이 유효합니다.\")발생\n" +
            " ")
    @GetMapping("/check/email")
    public ResponseEntity<?> checkDuplicateEmail(@RequestParam("email")String email) throws CustomException {
        return userRegistService.checkDuplicateEmail(email);
    }

    @Operation(summary = "이메일 전송(인증번호 전송)", description = "1. 메일번호를 RequrestParam으로 받음 -> 해당 메일로 인증번호 전송 : MAIL_SEND_ERR(-1500, \"메일 전송 과정 중 에러가 발생했습니다.\"),\n" +
            "2. -> BE 서버에서는 해당 인증 번호를 Redis 메모리에 저장(expired 기간 10분 등) : MAIL_AUTH_SAVE_ERR(-1501, \"인증번호를 Redis에 저장하는 중 에러가 발생했습니다.\")\n" +
            "\t: 이때 저장은 해당 유저의 인증번호(key) : IP(value)로 하여 여러 사용자가 하나의 인증번호를 돌려쓰지 못하게 함.")
    @GetMapping("/send-email")
    public ResponseEntity<?> sendAuthcodeToEmail(@RequestParam("email") String email, HttpServletRequest request) throws CustomException, MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendSimpleMessageRegist(email); //1. 해당 주소로 인증번호 포함된 메일 전송
        userRegistService.saveAuthCodeToRedis(authCode); //2. authCode 값을 생성 Redis에 보관(expire : 10분)
        return ResponseEntity.ok(new ResponseDTO(MAIL_SEND_IS_OK.getCode(), MAIL_SEND_IS_OK.getMessage()));
    }

    @Operation(summary = "인증번호 확인", description = "인증 번호 확인 : \n" +
            " 인증 번호 확인 후, 해당 Authcode를 키값에서 삭제한다. 에러의 경우 : REDIS_GET_VALUE_FAIL(-1600, \"redis 키값으로 value를 찾아오는 것을 실패했습니다. (입력 값 다름)\"),\n" +
            " MAIL_SEND_IS_OK(1400, \"이메일 전송과 인증번호 등록이 성공적으로 이루어졌습니다.\"),\n")
    @Transactional
    @GetMapping("/check/authcode-valid")
    public ResponseEntity<?> checkAuthcodeIsValid(@RequestParam String authcode) throws CustomException {
        userRegistService.checkAuthcode(authcode); //Authcode 맞는지 판별
        return ResponseEntity.ok(new ResponseDTO(MAIL_SEND_IS_OK.getCode(), MAIL_SEND_IS_OK.getMessage()));
    }

    @Operation(summary = "프로필 사진 업로드", description = "MultipartFile 형태로 파일을 올려주시면 됩니다. 리턴값으로 해당 주소의 Url을 가져옵니다.")
    @Transactional
    @PostMapping("/upload/profile")
    public String saveFile(HttpServletRequest request, @RequestParam("upload-file") MultipartFile multipartFile) throws IOException {
        return s3FileService.saveFile(request, multipartFile, "profile");
    }

    @Operation(summary = "[비밀번호]비밀번호 찾기 기능", description = "이메일 입력 후 클릭 시 해당 메일 존재 확인 후\n" +
            " 레디스에 임시 번호를 빌드합니다. (email : 임시 번호) (5분)\n" +
            "LOGIN_HAVE_NO_ACCOUT(-1101, \"일치하는 계정이 없습니다.\") : 계정이 없습니다.\n" +
            "MAIL_SEND_AND_RANDOM_PASSWORD_IS_OK(1401, \"이메일 전송과 임시 비밀번호 설정이 성공적으로 이루어졌습니다.\"),\n" +
            "그 외 레디스 오류")
    @GetMapping("/password/new")
    public ResponseEntity<?> sendEmailForPasswordAuth(@RequestParam String email) throws Exception {
        userRegistService.checkEmailExist(email); //이메일 존재 여부 확인
        String password = userRegistService.createPasswordCodeAndSaveRedis(email); //랜덤 난수 생성 후 레디스에 저장
        emailService.sendSimpleMessageChange(email, password); //난수 메일로 전송
        return ResponseEntity.ok(new ResponseDTO(MAIL_SEND_AND_RANDOM_CODE_IS_OK.getCode(), MAIL_SEND_AND_RANDOM_CODE_IS_OK.getMessage()));
    }

    @Transactional
    @Operation(summary = "[비밀번호]메일 인증 임시 코드 입력", description =
            "비밀번호 재설정 확인 및 임시 변경 로직입니다.\n" +
            "이메일과 인증코드 입력을 입력해서 맞는 경우 해당 비밀번호로 변경됩니다. 오류는 2가지입니다.\n"+
            "    REDIS_GET_VALUE_FAIL(-1600, \"redis 해당 키가 존재하지 않습니다\"),\n" +
                    "    REDIS_GET_MATCH_FAIL(-1601, \"redis 키값은 존재하나, 내용물이 다릅니다.\"),"
    )
    @PostMapping("/password/check")
    public ResponseEntity<?> findPassword(@RequestBody UserPasswordChangeDTO userPasswordChangeDTO) throws IOException {
        String emailKey = "[PW-CHECK-CODE]" + userPasswordChangeDTO.getEmail();
        String authcode = userPasswordChangeDTO.getAuthcode();
        userRegistService.checkPasswordAuthIsValid(emailKey, authcode); //입력 값 체크
        userRegistService.deleteAuthCode(emailKey); //레디스 값 삭제
        userRegistService.changePasswordByEmail(userPasswordChangeDTO.getEmail(), authcode);// DB 변경
        return ResponseEntity.ok(new ResponseDTO(MAIL_CHECK_IS_OK.getCode(), MAIL_CHECK_IS_OK.getMessage()));
    }

}
