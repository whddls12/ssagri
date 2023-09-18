package com.ssafy.ssagri.util.mail.repository;

import com.ssafy.ssagri.entity.email.SignUpEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignUpEmailLogRepository extends JpaRepository<SignUpEmailLog, Long> {
    Optional<SignUpEmailLog> findTopByEmailOrderByCreateTimeDesc(String email);
}
