package com.jo0oy.db.product.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document("product")
public class ProductEntity {

    @Id @Setter private String id;
    private String productName;
    private Integer productPrice;
    private String description;
    private List<ProductOptionGroupEntity> productOptionGroups;

    @Builder
    private ProductEntity(
        String id,
        String productName,
        Integer productPrice,
        String description,
        List<ProductOptionGroupEntity> productOptionGroups
    ) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;

        if (!CollectionUtils.isEmpty(productOptionGroups)) {
            this.productOptionGroups = new ArrayList<>();
            this.productOptionGroups.addAll(productOptionGroups);
        }
    }

}
