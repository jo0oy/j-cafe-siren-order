package com.jo0oy.api.user.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
    Long userId,
    String username,
    String phoneNumber
) {
}
