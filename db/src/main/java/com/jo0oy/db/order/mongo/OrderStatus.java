package com.jo0oy.db.order.mongo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    ORDER_COMPLETE("주문접수"),
    PICKUP_REQUESTED("픽업요청"),
    PICKUP_COMPLETE("픽업/주문완료"),
    CANCEL("주문취소");

    private final String description;

}
