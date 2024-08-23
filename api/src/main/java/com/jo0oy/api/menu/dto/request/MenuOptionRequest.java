package com.jo0oy.api.menu.dto.request;

public record MenuOptionRequest(
    String menuOptionName,
    Integer menuOptionPrice,
    Integer defaultValue,
    Integer maxValue
) {
}
