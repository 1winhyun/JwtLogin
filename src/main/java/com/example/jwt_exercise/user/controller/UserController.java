package com.example.jwt_exercise.user.controller;

import com.example.jwt_exercise.user.dto.LoginResponseDTO;
import com.example.jwt_exercise.user.dto.UserRequestDTO;
import com.example.jwt_exercise.user.dto.UserResponseDTO;
import com.example.jwt_exercise.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO requestDTO){
        UserResponseDTO responseDTO=userService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserRequestDTO requestDTO){
        LoginResponseDTO responseDTO=userService.login(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
