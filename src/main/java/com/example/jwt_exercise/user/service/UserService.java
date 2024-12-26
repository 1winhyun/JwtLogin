package com.example.jwt_exercise.user.service;

import com.example.jwt_exercise.user.config.jwt.TokenProvider;
import com.example.jwt_exercise.user.domain.User;
import com.example.jwt_exercise.user.dto.LoginResponseDTO;
import com.example.jwt_exercise.user.dto.UserRequestDTO;
import com.example.jwt_exercise.user.dto.UserResponseDTO;
import com.example.jwt_exercise.user.repository.UserRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("unexpecteduser"));
    }

    @Transactional
    public UserResponseDTO register(UserRequestDTO requestDTO){
        if (userRepository.existsByName(requestDTO.getName())) {

            throw new IllegalArgumentException("이미 존재합니다.");
        }
        User user=User.builder()
                .name(requestDTO.getName())
                .password(bCryptPasswordEncoder.encode(requestDTO.getPassword()))
                .build();
        userRepository.save(user);
        return new UserResponseDTO(user.getId(),"회원가입 완료");
    }

    @Transactional
    public LoginResponseDTO login(UserRequestDTO requestDTO){
        User user=userRepository.findByName(requestDTO.getName())
                .orElseThrow(()->new IllegalArgumentException("사용자가 없는데?"));

        if(!bCryptPasswordEncoder.matches(requestDTO.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 달라");
        }

        String accessToken=tokenProvider.generateToken(user, Duration.ofHours(2));
        String refreshToken=tokenProvider.generateToken(user,Duration.ofHours(7));

        refreshTokenService.saveOrUpdateRefreshToken(user.getId(),refreshToken);

        return new LoginResponseDTO(user.getName(),accessToken,"로그인에 성공하였습니다.");
    }
}
