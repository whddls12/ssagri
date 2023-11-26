package com.ssafy.ssagri.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("registry : {}", registry);
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:5173", "http://localhost:5000", "https://j9b209.p.ssafy.io")
                .withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        log.info("Broker registry : {}", registry);
        // /simple 경로로 시작하는 STOMP 메시지의 Destination 헤더는 @Controller 객체의 @MessageMapping 메서드로 라우팅된다.
        registry.setApplicationDestinationPrefixes("/simple");

        // /topic 또는 /queue로 시작하는 Destination 헤더를 가진 메시지를 브로커로 라우팅한다.
        // /topic, /queue prefix는 단순히 메시지가 pub-sub, point-to-point 인지 여부를 나타내는 컨벤션일 뿐이며,
        // 외부 브로커를 사용할 경우에는 해당 Destination 헤더 prefix가 달라질 수 있다.
        registry.enableSimpleBroker("/topic", "/queue");
    }


}
