package com.jo0oy.api.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ProductOptionResponse(
    String productOptionName,
    Integer productOptionUnitPrice,
    Integer defaultCount,
    Integer maxCount
) {
}
