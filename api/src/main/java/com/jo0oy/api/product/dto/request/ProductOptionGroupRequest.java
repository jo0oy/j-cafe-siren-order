package com.jo0oy.api.product.dto.request;

import java.util.List;

public record ProductOptionGroupRequest(
    String productOptionGroupName,
    List<ProductOptionRequest> productOptions
) {
}
