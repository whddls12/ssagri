package com.ssafy.ssagri.util.mail;

import org.springframework.stereotype.Component;

/**
 * 메일에 들어갈 텍스트를 관리합니다.
 */
@Component
public class MailText {

    /**
     * 계정 확인 인증 메일
     * @param authCode
     * @return msgg
     */
    public String getMessageChangeText(String authCode) {
        String msgg = "";
        msgg += "<table style='    width: 100% !important;    background: #ffffff;    margin: 0;    padding: 0;    min-width: 100%;    font-family: 'Malgun Gothic', 'Dotum', 'sans-serif';   '>";
        msgg += "<tr><td style='text-align: center;'>";
        msgg += "<img src='https://learners-high.s3.ap-northeast-2.amazonaws.com/mail/trade.jpg' alt='header' loading='lazy'>";
        msgg += "</td></tr><tr><td style='text-align: center;'>";
        msgg += "<div style='margin-top: 60px; margin-bottom: 30px;'>";
        msgg += "<h1 style='margin-bottom: 40px;'>계정 인증 안내</h1>";
        msgg += "<p style='margin-top: 0; margin-bottom: 15px; line-height:2;'>";
        msgg += "안녕하세요. 싸피인들의 슬기로운 거래생활, 싸그리입니다. <br>";
        msgg += "비밀번호 재설정 탭에서 아래의 인증번호를 입력해주세요. 입력 시 해당 비밀번호가 임시 비밀번호로 설정됩니다.<br>";
        msgg += "(인증번호는 5분간 유효합니다.)";
        msgg += "</p></div></td></tr><tr><td style='text-align: center;'>";
        msgg += "<div style='margin-bottom: 60px;'>";
        msgg += "<p style='display:inline-block;padding:20px 80px;font-size:16px;font-weight:bold;color:#fff;background:#9a50ff;'>";
        msgg += "인증번호: " + authCode;
        msgg += "</p></div></td></tr><tr><td style='text-align: center;'>";
        msgg += "<img src='https://learners-high.s3.ap-northeast-2.amazonaws.com/mail/SSAFY+mark.png' alt='footer' loading='lazy'>";
        msgg += "</td></tr></tbody></table>";
        return msgg;
    }

    /**
     * 회원가입 등록 메시지
     * @param authCode
     * @return msgg
     */
    public String getMessageRegistText(String authCode) {
        String msgg="";
        msgg += "<table style='    width: 100% !important;    background: #ffffff;    margin: 0;    padding: 0;    min-width: 100%;    font-family: 'Malgun Gothic', 'Dotum', 'sans-serif';   '>";
        msgg += "<tr><td style='text-align: center;'>";
        msgg += "<img src='https://learners-high.s3.ap-northeast-2.amazonaws.com/mail/trade.jpg' alt='header' loading='lazy'>";
        msgg += "</td></tr><tr><td style='text-align: center;'>";
        msgg += "<div style='margin-top: 60px; margin-bottom: 30px;'>";
        msgg += "<h1 style='margin-bottom: 40px;'>계정 인증 안내</h1>";
        msgg += "<p style='margin-top: 0; margin-bottom: 15px; line-height:2;'>";
        msgg += "안녕하세요. 싸피인들의 슬기로운 거래생활, 싸그리입니다. <br>";
        msgg += "가입을 위해 아래 인증번호를 입력하시고 본인 인증을 완료해주세요.<br>";
        msgg += "(인증번호는 10분간 유효합니다.)";
        msgg += "</p></div></td></tr><tr><td style='text-align: center;'>";
        msgg += "<div style='margin-bottom: 60px;'>";
        msgg += "<p style='display:inline-block;padding:20px 80px;font-size:16px;font-weight:bold;color:#fff;background:#9a50ff;'>";
        msgg += "인증번호: " + authCode;
        msgg += "</p></div></td></tr><tr><td style='text-align: center;'>";
        msgg += "<img src='https://learners-high.s3.ap-northeast-2.amazonaws.com/mail/SSAFY+mark.png' alt='footer' loading='lazy'>";
        msgg += "</td></tr></tbody></table>";
        return msgg;
    }
}
