package com.jo0oy.api.product.dto.request;

import java.util.List;

public record ProductRequest(
    String productName,
    Integer productPrice,
    String description,
    List<ProductOptionGroupRequest> productOptionGroups
) {
}
