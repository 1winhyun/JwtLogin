package com.example.jwt_exercise.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccessTokenResponseDTO {
    private String accessToken;
}
