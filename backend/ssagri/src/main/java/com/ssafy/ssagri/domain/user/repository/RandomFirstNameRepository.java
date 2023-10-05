package com.ssafy.ssagri.domain.user.repository;

import com.ssafy.ssagri.entity.user.RandomNicknameFirst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RandomFirstNameRepository extends JpaRepository<RandomNicknameFirst, Long> {
    @Query(value = "SELECT firstName FROM random_nickname_first ORDER BY RAND() LIMIT 1", nativeQuery = true)
    String getRandomName();

}