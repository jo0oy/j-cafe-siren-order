package com.jo0oy.api.menu.dto.response;

import lombok.Builder;

@Builder
public record MenuOptionResponse(
    String menuOptionName,
    Integer menuOptionPrice,
    Integer defaultValue,
    Integer maxValue
) {
}
