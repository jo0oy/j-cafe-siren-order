package com.jo0oy.db.menu.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document("menu_option_group")
public class MenuOptionGroupEntity {

    private String menuOptionGroupName;
    private List<MenuOptionEntity> menuOptions;

    @Builder
    private MenuOptionGroupEntity(
        String menuOptionGroupName,
        List<MenuOptionEntity> menuOptions
    ) {
        this.menuOptionGroupName = menuOptionGroupName;

        if (!CollectionUtils.isEmpty(menuOptions)) {
            this.menuOptions = new ArrayList<>();
            this.menuOptions.addAll(menuOptions);
        }
    }
}
