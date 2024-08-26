package com.jo0oy.api.product.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductOptionGroupResponse(
    String productOptionGroupName,
    List<ProductOptionResponse> productOptions
) {
}
