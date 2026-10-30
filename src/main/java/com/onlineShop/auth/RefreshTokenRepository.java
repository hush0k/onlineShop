package com.onlineShop.auth;

import com.onlineShop.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByUser(User user);

    Optional<RefreshToken> findByTokenHash(String tokenHash);
}
