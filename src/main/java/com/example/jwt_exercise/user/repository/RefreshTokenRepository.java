package com.example.jwt_exercise.user.repository;

import com.example.jwt_exercise.user.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken>findByUserId(Long userId);
    Optional<RefreshToken>findByRefreshToken(String refreshToken);
}
