package com.jo0oy.api.product.dto.request;

public record ProductOptionRequest(
    String productOptionName,
    Integer productOptionUnitPrice,
    Integer defaultCount,
    Integer maxCount
) {
}
