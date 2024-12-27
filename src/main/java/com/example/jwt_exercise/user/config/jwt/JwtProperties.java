package com.example.jwt_exercise.user.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("jwt")//자바 클래스에 프로퍼티 값을 가져와서 사용하는 어노테이션
public class JwtProperties {
    private String issuer;//토큰 발급자
    private String secretKey;//서명, 비밀값
}
