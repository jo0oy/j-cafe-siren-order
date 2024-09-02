package com.jo0oy.api.order.dto.request;

import java.util.List;

public record OrderProductRequest(
    String orderProductName,
    Integer orderProductBasePrice,
    Integer orderProductTotalCount,
    Integer orderProductTotalAmount,
    List<OrderProductOptionRequest> orderProductOptions
) {
}
