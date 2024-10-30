package com.jo0oy.api.security.jwt;

import com.jo0oy.api.global.error.JwtErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.api.security.AuthUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenService implements InitializingBean {

    private SecretKey secretKey;
    private final AuthUserDetailsService authUserDetailsService;
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 1; // 1분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 3; // 3분

    @Override
    public void afterPropertiesSet() throws Exception {
        this.secretKey = Jwts.SIG.HS512.key().build();
    }

    // jwt 토큰 생성
    public AuthTokenResponse generateToken(Authentication authentication) {

        var authorities = getAuthorities(authentication);
        long now = (new Date()).getTime();
        Date validity = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenValidity = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        var accessToken = Jwts.builder()
            .subject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(secretKey)
            .expiration(validity)
            .compact();

        var refreshToken = Jwts.builder()
            .expiration(refreshTokenValidity)
            .signWith(secretKey).compact();

        return AuthTokenResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
            .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

            return true;
        } catch (SignatureException | MalformedJwtException e) {
            log.info("Invalid JWT signature: {}", e.getMessage());
            throw new ApiException(JwtErrorCode.INVALID_JWT);
        } catch (ExpiredJwtException e) {
            log.info("JWT has expired: {}", e.getMessage());
            throw new ApiException(JwtErrorCode.JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.info("JWT not supported: {}", e.getMessage());
            throw new ApiException(JwtErrorCode.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty: {}", e.getMessage());
            throw new ApiException(JwtErrorCode.ILLEGAL_JWT);
        }
    }

    // 인증 객체 반환
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        var principal = authUserDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    // 토큰 내용 파싱
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (ExpiredJwtException ex) {
            return ex.getClaims();
        }
    }

    // 권한 리스트 추출
    private Collection<String> getAuthorities(Authentication authentication) {
        return Collections.singletonList(authentication.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null));
    }
}
