package com.jo0oy.api.security.jwt;

import lombok.Builder;

@Builder
public record AuthTokenResponse(
    String accessToken,
    String refreshToken,
    Long refreshTokenExpirationTime
) {
}
