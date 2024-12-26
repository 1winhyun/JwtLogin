package com.example.jwt_exercise.user.service;

import com.example.jwt_exercise.user.domain.RefreshToken;
import com.example.jwt_exercise.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new IllegalArgumentException("unexpectedtoken"));
    }

    @Transactional
    public void saveOrUpdateRefreshToken(Long userId, String refreshToken){
        RefreshToken token = refreshTokenRepository.findByUserId(userId)
                .map(existingToken -> {
                    existingToken.update(refreshToken);
                    return existingToken;
                })
                .orElseGet(() -> new RefreshToken(userId, refreshToken));

        refreshTokenRepository.save(token);
    }
}
