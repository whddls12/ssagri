package com.ssafy.ssagri.domain.user.repository;

import com.ssafy.ssagri.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface UserRegistAndModifyRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.nickname = :nickname")
    boolean isNicknameExists(@Param("nickname") String nickname);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean isEmailExists(@Param("email") String email);

    User findByNo(Long no);

    // 이미지 변경
    @Modifying
    @Transactional
    @Query("update User u SET u.profile = :newProfile WHERE u.no = :userNo")
    void updateImage(@Param("newProfile") String newProfile, @Param("userNo") Long userNo);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.no = :userNo and u.userCreateType = 'NORMAL'")
    boolean UserTypeIsNormal(@Param("userNo") Long userNo);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.no = :userNo")
    void changeUserPassword(@Param("password") String password, @Param("userNo") Long userNo);

    @Query("SELECT u.no FROM User u WHERE u.email = :email")
    Long getUserNoByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.nickname = :nickname WHERE u.no = :userNo")
    void changeUserNickname(@Param("nickname") String nickname, @Param("userNo") Long userNo);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userDeleteDate = :now WHERE u.no = :userNo")
    void deleteUser(@Param("now") LocalDateTime now, @Param("userNo") Long userNo);
}
