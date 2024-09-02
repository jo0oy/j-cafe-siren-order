package com.jo0oy.db.order.mongo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    TOGO("포장"),
    FOR_HERE("매장");

    private final String description;

}
