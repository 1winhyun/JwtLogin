package com.example.jwt_exercise.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequestDTO {
    private String refreshToken;
}
