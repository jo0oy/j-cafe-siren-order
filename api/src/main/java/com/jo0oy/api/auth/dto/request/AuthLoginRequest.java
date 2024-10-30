package com.jo0oy.api.auth.dto.request;

public record AuthLoginRequest(
    String username,
    String password
) {
}
