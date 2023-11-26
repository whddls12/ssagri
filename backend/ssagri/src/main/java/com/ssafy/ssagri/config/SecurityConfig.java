package com.ssafy.ssagri.config;


import com.ssafy.ssagri.util.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity //Spring Security를 웹 애플리케이션에서 활성화하도록 지시
@Configuration //이 클래스 내에서 Spring Bean을 정의하고 설정을 구성
public class SecurityConfig {

    @Autowired
    CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // common settings
                    .httpBasic().disable()
                    .csrf().disable()   //CSRF 토큰을 사용하지 않도록 설정
                    .cors()             //CORS 활성화
                    .configurationSource(corsConfigurationSource)
                .and()
                    .sessionManagement()//세션 관리 설정
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 생성 X, 요청 상태 추적하지 않음
                .and()
                    .authorizeRequests()
                    .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**") // Swagger 경로
                    .permitAll() // 해당 경로에 대한 접근을 모두 허용
                .and()
        //jwt Filter Setting
                    .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

}
