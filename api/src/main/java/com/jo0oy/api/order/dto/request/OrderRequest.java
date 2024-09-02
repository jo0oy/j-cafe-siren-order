package com.jo0oy.api.order.dto.request;

import com.jo0oy.db.order.mongo.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
    String id,
    Long userId,
    Long storeId,
    String storeName,
    Integer totalCount,
    Integer totalAmount,
    String orderType,
    String requestComment,
    OrderStatus orderStatus,
    LocalDateTime orderedAt,
    LocalDateTime lastModifiedAt,
    List<OrderProductRequest> orderProducts
) {
}
