package com.jo0oy.api.order.dto.request;

public record OrderProductOptionRequest(
    String orderProductOptionName,
    Integer orderProductOptionUnitPrice,
    Integer optionTotalCount,
    Integer optionTotalAmount
) {
}
