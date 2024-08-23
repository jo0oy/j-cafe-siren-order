package com.jo0oy.api.menu.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MenuResponse(
    String id,
    String menuName,
    Integer menuPrice,
    String description,
    List<MenuOptionGroupResponse> menuOptionGroups
) {
}
