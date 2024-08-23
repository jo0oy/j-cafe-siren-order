package com.jo0oy.api.menu.business;

import com.jo0oy.api.global.annotation.Business;
import com.jo0oy.api.menu.converter.MenuConverter;
import com.jo0oy.api.menu.dto.request.MenuRequest;
import com.jo0oy.api.menu.dto.response.MenuResponse;
import com.jo0oy.api.menu.service.MenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class MenuBusiness {

    private final MenuService menuService;
    private final MenuConverter menuConverter;

    public void register(MenuRequest request) {
        var menuEntity = menuConverter.toEntity(request);
        menuService.save(menuEntity);
    }

    public MenuResponse getMenuById(String menuId) {
        var menuEntity = menuService.findById(menuId);
        return menuConverter.toResponse(menuEntity);
    }

    public List<MenuResponse> getMenuList() {
        return menuService.findAll()
            .stream()
            .map(menuConverter::toResponse)
            .collect(Collectors.toList());
    }

    public MenuResponse update(String menuId, MenuRequest request) {
        var updateRequestEntity = menuConverter.toEntity(request);
        updateRequestEntity.setId(menuId);

        var updatedEntity = menuService.update(updateRequestEntity);

        return menuConverter.toResponse(updatedEntity);
    }

    public void delete(String menuId) {
        menuService.delete(menuId);
    }
}
