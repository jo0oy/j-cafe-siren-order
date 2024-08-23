package com.jo0oy.api.menu.dto.request;

import java.util.List;

public record MenuRequest(
    String menuName,
    Integer menuPrice,
    String description,
    List<MenuOptionGroupRequest> menuOptionGroups
) {
}
