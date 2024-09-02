package com.jo0oy.db.order.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("order_product_option")
public class OrderProductOptionEntity {

    private String orderProductOptionName;
    private Integer orderProductOptionTotalCount;
    private Integer orderProductOptionTotalAmount;

    @Builder
    private OrderProductOptionEntity(
        String orderProductOptionName,
        Integer orderProductOptionTotalCount,
        Integer orderProductOptionTotalAmount
    ) {
        this.orderProductOptionName = orderProductOptionName;
        this.orderProductOptionTotalCount = orderProductOptionTotalCount;
        this.orderProductOptionTotalAmount = orderProductOptionTotalAmount;
    }

}
