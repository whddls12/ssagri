package com.ssafy.ssagri.domain.redis;

import com.ssafy.ssagri.entity.user.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//not used now
@Repository
public interface UserTokenRepository extends CrudRepository<RefreshToken, String> {
}
