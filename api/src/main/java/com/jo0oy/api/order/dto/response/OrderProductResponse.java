package com.jo0oy.api.order.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderProductResponse(
    String orderProductName,
    Integer orderProductBasePrice,
    Integer orderProductTotalCount,
    Integer orderProductTotalAmount,
    List<OrderProductOptionResponse> orderProductOptions
) {
}
