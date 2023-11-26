package com.ssafy.ssagri.util.mail;

import com.ssafy.ssagri.entity.email.EmailSendLog;
import com.ssafy.ssagri.entity.email.SignUpEmailLog;
import com.ssafy.ssagri.util.exception.CustomException;
import com.ssafy.ssagri.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import static com.ssafy.ssagri.util.exception.CustomExceptionStatus.MAIL_SEND_ERR;

/**
 * 이메일 서비스를 관리하고 메일 인증을 보내는 로직
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
//    private final EmailSendLogRepository emailSendLogRepository;
//    private final SignUpEmailLogRepository signupEmailLogRepository;
    private final MailText mailText;
    SignUpEmailLog signupEmailLog;
    EmailSendLog emailSendLog;

    private MimeMessage createMessageChange(String to, String authCode) throws MessagingException, UnsupportedEncodingException {
        log.info("[Mail]비밀번호 재설정 : 보내는 대상 : {}, 인증 번호 {}", to, authCode);
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("[SSAGRI] 비밀번호 재설정 이메일입니다.");//제목
        message.setText(mailText.getMessageChangeText(authCode), "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ssagri9th@gmail.com","SSAGRI"));//보내는 사람

        return message;
    }

    private MimeMessage createMessageRegist(String to, String authCode) throws CustomException, MessagingException, UnsupportedEncodingException {
        log.info("[Mail]신규 가입 : 보내는 대상 : {}, 인증 번호 {}", to, authCode);
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("[SSAGRI] 회원가입 인증 요청입니다.");//제목
        message.setText(mailText.getMessageRegistText(authCode), "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ssagri9th@gmail.com","SSAGRI"));//보내는 사람
        return message;
    }

    public static String createKey() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        int length = 16;
        SecureRandom rnd = new SecureRandom();

        StringBuilder key = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            key.append(characters.charAt(rnd.nextInt(characters.length())));
        }

        return key.toString();
    }

    public String sendSimpleMessageChange(String to, String authCode)throws Exception {
//        String authCode =  createKey(); // 인증코드 생성
        MimeMessage message = createMessageChange(to, authCode); // 메시지 생성
        try{ // 예외처리
            javaMailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return authCode;
    }

    public String sendSimpleMessageRegist(String to) throws CustomException, MessagingException, UnsupportedEncodingException {
        String authCode =  createKey(); // 인증코드 생성
        MimeMessage message = createMessageRegist(to, authCode); // 메시지 생성
        try{
            javaMailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
            throw new CustomException(MAIL_SEND_ERR);
        }
        return authCode;
    }
}
