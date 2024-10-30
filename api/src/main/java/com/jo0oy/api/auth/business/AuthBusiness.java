package com.jo0oy.api.auth.business;

import com.jo0oy.api.auth.dto.request.AuthLoginRequest;
import com.jo0oy.api.global.annotation.Business;
import com.jo0oy.api.security.jwt.AuthTokenResponse;
import com.jo0oy.api.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Business
public class AuthBusiness {

    private static final String REFRESH_TOKEN_KEY_PREFIX = "RT:"; // redis 에 저장되는 refreshToken key = RT:{username}
    private static final String LOGOUT_KEY_PREFIX = "LOGOUT:"; // redis 에 저장되는 로그아웃 key = LOGOUT:{AccessToken}
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthTokenResponse login(AuthLoginRequest request) {

        log.info("로그인 및 인증 토큰 발행 로직 실행");

        var authenticationToken
            = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        var tokenResponse = jwtTokenService.generateToken(authentication);

        redisTemplate.opsForValue().set(REFRESH_TOKEN_KEY_PREFIX + authentication.getName(),
            tokenResponse.refreshToken(), tokenResponse.refreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenResponse;
    }
}
