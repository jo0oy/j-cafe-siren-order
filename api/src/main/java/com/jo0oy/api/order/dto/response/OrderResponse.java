package com.jo0oy.api.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jo0oy.db.order.mongo.OrderStatus;
import com.jo0oy.db.order.mongo.OrderType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record OrderResponse(
    String orderId,
    Long userId,
    Long storeId,
    String storeName,
    Integer totalCount,
    Integer totalAmount,
    OrderType orderType,
    String requestComment,
    OrderStatus orderStatus,
    LocalDateTime orderedAt,
    LocalDateTime lastModifiedAt,
    List<OrderProductResponse> orderProducts
) {
}
