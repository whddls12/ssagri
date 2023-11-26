package com.ssafy.ssagri.entity.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
public class SignUpEmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signupEmailLogNo;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String verificationCode;
    @Column(nullable = false)
    private LocalDateTime createTime;
    @Column(nullable = false)
    private LocalDateTime expiryTime;
    @Column(nullable = false)
    private boolean used;

    public SignUpEmailLog() {

    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
