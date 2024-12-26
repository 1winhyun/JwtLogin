package com.example.jwt_exercise.user.controller;

import com.example.jwt_exercise.user.config.jwt.TokenProvider;
import com.example.jwt_exercise.user.dto.CreateAccessTokenRequestDTO;
import com.example.jwt_exercise.user.dto.CreateAccessTokenResponseDTO;
import com.example.jwt_exercise.user.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponseDTO>createNewAccessToken(@RequestBody CreateAccessTokenRequestDTO requestDTO){
        String accessToken=tokenService.createNewAccessToken(requestDTO.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDTO(accessToken));
    }
}
