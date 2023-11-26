package com.ssafy.ssagri.domain.user.repository;

import com.ssafy.ssagri.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginAndLogoutRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email and u.password =:password")
    boolean isAccountIsExists(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u.no FROM User u WHERE u.email =:email")
    Long getUserNoUsingEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email and u.userDeleteDate is not null")
    boolean isAccountDeleted(@Param("email") String email);
}
