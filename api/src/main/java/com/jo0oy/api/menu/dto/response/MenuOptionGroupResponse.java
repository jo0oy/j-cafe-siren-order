package com.jo0oy.api.menu.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MenuOptionGroupResponse(
    String menuOptionGroupName,
    List<MenuOptionResponse> menuOptions
) {
}
