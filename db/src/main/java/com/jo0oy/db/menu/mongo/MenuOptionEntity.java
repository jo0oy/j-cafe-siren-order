package com.jo0oy.db.menu.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("menu_option")
public class MenuOptionEntity {

    private String menuOptionName;
    private int menuOptionPrice;
    private int defaultAmount;
    private int maxAmount;

    @Builder
    private MenuOptionEntity(
        String menuOptionName,
        int menuOptionPrice,
        int defaultAmount,
        int maxAmount
    ) {
        this.menuOptionName = menuOptionName;
        this.menuOptionPrice = menuOptionPrice;
        this.defaultAmount = defaultAmount;
        this.maxAmount = maxAmount;
    }
}
