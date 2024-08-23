package com.jo0oy.db.menu.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document("menu")
public class MenuEntity {

    @Id
    private String id;
    private String menuName;
    private int menuPrice;
    private String description;
    private List<MenuOptionGroupEntity> menuOptionGroups;

    @Builder
    public MenuEntity(
        String id,
        String menuName,
        int menuPrice,
        String description,
        List<MenuOptionGroupEntity> menuOptionGroups
    ) {
        this.id = id;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.description = description;

        if (!CollectionUtils.isEmpty(menuOptionGroups)) {
            this.menuOptionGroups = new ArrayList<>();
            this.menuOptionGroups.addAll(menuOptionGroups);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

}
