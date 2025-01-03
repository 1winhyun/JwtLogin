package com.example.jwt_exercise.user.config.jwt;

import com.example.jwt_exercise.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt){
        Date now=new Date();
        return makeToken(new Date(now.getTime()+expiredAt.toMillis()),user);
    }

    private String makeToken(Date expiry,User user){
        Date now=new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getName())
                .claim("id",user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Authentication getAuthentication(String token){
        Claims claims=getClaims(token);
        String name = claims.getSubject();//@AuthenticationPrincipal 을 사용하고 싶다면 인증을 가져오는 메서드에 인증할 변수를 반드시 추가해줘야한다.
        Set<SimpleGrantedAuthority> authorities= Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(name,token,authorities);
    }

    public Long getUserId(String token){
        Claims claims=getClaims(token);
        return claims.get("id", Long.class);
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
