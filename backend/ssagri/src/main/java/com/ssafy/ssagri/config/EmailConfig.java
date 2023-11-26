package com.ssafy.ssagri.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 이메일에 관련된 설정
 */
@Configuration
public class EmailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String hostEmail;

    @Value("${mail.password}")
    private String hostPassword;

    @Value("${mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${mail.properties.mail.smtp.starttls.enable}")
    private String starttls;
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(hostEmail);
        mailSender.setPassword(hostPassword);
        mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", auth);
        mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", starttls);
        return mailSender;
    }
}