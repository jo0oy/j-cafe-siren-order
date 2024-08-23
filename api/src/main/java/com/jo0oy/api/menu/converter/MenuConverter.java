package com.jo0oy.api.menu.converter;

import com.jo0oy.api.global.annotation.Converter;
import com.jo0oy.api.menu.dto.request.MenuOptionGroupRequest;
import com.jo0oy.api.menu.dto.request.MenuOptionRequest;
import com.jo0oy.api.menu.dto.request.MenuRequest;
import com.jo0oy.api.menu.dto.response.MenuOptionGroupResponse;
import com.jo0oy.api.menu.dto.response.MenuOptionResponse;
import com.jo0oy.api.menu.dto.response.MenuResponse;
import com.jo0oy.db.menu.mongo.MenuEntity;
import com.jo0oy.db.menu.mongo.MenuOptionEntity;
import com.jo0oy.db.menu.mongo.MenuOptionGroupEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Converter
public class MenuConverter {

    public MenuEntity toEntity(MenuRequest request) {

        // MenuOptionGroupRequest --> MenuOptionGroupEntity 변환
        List<MenuOptionGroupEntity> menuOptionGroupEntities = null;
        if (!CollectionUtils.isEmpty(request.menuOptionGroups())) {
            menuOptionGroupEntities
                = request.menuOptionGroups().stream()
                .map(this::toEntity)
                .toList();
        }

        return MenuEntity.builder()
            .menuName(request.menuName())
            .menuPrice(request.menuPrice())
            .description(request.description())
            .menuOptionGroups(menuOptionGroupEntities)
            .build();
    }

    public MenuOptionGroupEntity toEntity(MenuOptionGroupRequest request) {

        // MenuOptionRequest --> MenuOptionEntity 변환
        List<MenuOptionEntity> menuOptionEntities = null;
        if (!CollectionUtils.isEmpty(request.menuOptions())) {
            menuOptionEntities
                = request.menuOptions()
                .stream()
                .map(this::toEntity)
                .toList();
        }

        return MenuOptionGroupEntity.builder()
            .menuOptionGroupName(request.menuOptionGroupName())
            .menuOptions(menuOptionEntities)
            .build();
    }

    public MenuOptionEntity toEntity(MenuOptionRequest request) {
        return MenuOptionEntity.builder()
            .menuOptionName(request.menuOptionName())
            .menuOptionPrice(request.menuOptionPrice())
            .defaultAmount(
                Objects.nonNull(request.defaultValue()) ? request.defaultValue() : 0
            )
            .maxAmount(
                Objects.nonNull(request.maxValue()) ? request.maxValue() : 0
            )
            .build();
    }

    public MenuResponse toResponse(MenuEntity entity) {

        // MenuOptionGroupEntity --> MenuOptionGroupResponse 변환
        List<MenuOptionGroupResponse> menuOptionGroupResponses = null;
        if (!CollectionUtils.isEmpty(entity.getMenuOptionGroups())) {
            menuOptionGroupResponses
                = entity.getMenuOptionGroups()
                .stream()
                .map(this::toResponse)
                .toList();
        }

        return MenuResponse.builder()
            .id(entity.getId())
            .menuName(entity.getMenuName())
            .menuPrice(entity.getMenuPrice())
            .description(entity.getDescription())
            .menuOptionGroups(menuOptionGroupResponses)
            .build();
    }

    public MenuOptionGroupResponse toResponse(MenuOptionGroupEntity entity) {

        // MenuOptionEntity --> MenuOptionResponse 변환
        List<MenuOptionResponse> menuOptionResponses = null;
        if (!CollectionUtils.isEmpty(entity.getMenuOptions())) {
            menuOptionResponses
                = entity.getMenuOptions()
                .stream()
                .map(this::toResponse)
                .toList();
        }

        return MenuOptionGroupResponse.builder()
            .menuOptionGroupName(entity.getMenuOptionGroupName())
            .menuOptions(menuOptionResponses)
            .build();
    }

    public MenuOptionResponse toResponse(MenuOptionEntity entity) {
        return MenuOptionResponse.builder()
            .menuOptionName(entity.getMenuOptionName())
            .menuOptionPrice(entity.getMenuOptionPrice())
            .defaultValue(entity.getDefaultAmount())
            .maxValue(entity.getMaxAmount())
            .build();
    }
}
