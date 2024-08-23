package com.jo0oy.api.menu.dto.request;

import java.util.List;

public record MenuOptionGroupRequest(
    String menuOptionGroupName,
    List<MenuOptionRequest> menuOptions
) {
}
