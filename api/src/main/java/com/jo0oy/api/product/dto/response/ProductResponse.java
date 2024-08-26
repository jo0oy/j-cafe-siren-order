package com.jo0oy.api.product.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
    String id,
    String productName,
    Integer productPrice,
    String description,
    List<ProductOptionGroupResponse> productOptionGroups
) {
}
