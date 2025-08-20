package com.htet.employeemanagementapi.security;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.lifespan}")
    private int tokenLifeSpan;
    @Value("${jwt.refreshLifespan}")
    private int refreshTokenLifeSpan;
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;


    @PostConstruct
    private void postConstruct(){
        var bytes = Base64.getDecoder().decode(secret);
        secretKey = new SecretKeySpec(bytes, "HmacSHA512");
    }

    public String generateToken(Authentication auth, boolean isRefreshToken){
        if (auth != null && auth.isAuthenticated()){
            var issueAt = new Date();
            var calender = Calendar.getInstance();
            calender.setTime(issueAt);

            if (isRefreshToken){
                calender.add(Calendar.HOUR, refreshTokenLifeSpan);
            }else {
                calender.add(Calendar.MINUTE, tokenLifeSpan);
            }
            var role = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList().getFirst();
            return Jwts.builder()
                    .subject(auth.getName())
                    .issuer(issuer)
                    .issuedAt(issueAt)
                    .expiration(calender.getTime())
                    .claim("role",role)
                    .signWith(secretKey)
                    .compact();
        }
        return null;
    }

    public Authentication parseToken(String token){
        if (!StringUtils.hasLength(token)){
            return null;
        }

        var jwt = Jwts.parser()
                .requireIssuer(issuer)
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        var email = jwt.getBody().getSubject();
        var role = jwt.getPayload().get("role",String.class);
        var authorities = List.of(new SimpleGrantedAuthority(role));
        return UsernamePasswordAuthenticationToken.authenticated(email,null,authorities);
    }
}
