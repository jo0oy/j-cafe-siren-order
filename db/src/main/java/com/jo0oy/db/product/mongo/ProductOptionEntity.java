package com.jo0oy.db.product.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("product_option")
public class ProductOptionEntity {

    private String productOptionName;
    private Integer productOptionUnitPrice;
    private Integer defaultCount;
    private Integer maxCount;

    @Builder
    private ProductOptionEntity(
        String productOptionName,
        Integer productOptionUnitPrice,
        Integer defaultCount,
        Integer maxCount
    ) {
        this.productOptionName = productOptionName;
        this.productOptionUnitPrice = productOptionUnitPrice;
        this.defaultCount = defaultCount;
        this.maxCount = maxCount;
    }

}
