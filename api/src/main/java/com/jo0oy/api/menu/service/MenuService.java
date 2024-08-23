package com.jo0oy.api.menu.service;

import com.jo0oy.api.global.error.MenuErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.db.menu.mongo.MenuEntity;
import com.jo0oy.db.menu.mongo.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public void save(MenuEntity menuEntity) {
        menuRepository.save(menuEntity);
    }

    public MenuEntity findById(String menuId) {
        return menuRepository.findById(menuId)
            .orElseThrow(
                () -> new ApiException(MenuErrorCode.MENU_NOT_FOUND)
            );
    }

    public MenuEntity update(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }

    public void delete(String menuId) {
        var deleteEntity = findById(menuId);
        menuRepository.delete(deleteEntity);
    }
}
