package com.jo0oy.api.user.dto.request;

public record UserRequest(
    String username,
    String password,
    String phoneNumber
) {
}
