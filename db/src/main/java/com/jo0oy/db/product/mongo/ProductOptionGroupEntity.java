package com.jo0oy.db.product.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document("product_option_group")
public class ProductOptionGroupEntity {

    private String productOptionGroupName;
    private List<ProductOptionEntity> productOptions;

    @Builder
    private ProductOptionGroupEntity(
        String productOptionGroupName,
        List<ProductOptionEntity> productOptions
    ) {
        this.productOptionGroupName = productOptionGroupName;

        if (!CollectionUtils.isEmpty(productOptions)) {
            this.productOptions = new ArrayList<>();
            this.productOptions.addAll(productOptions);
        }
    }

}
