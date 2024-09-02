package com.jo0oy.db.order.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document("order_product")
public class OrderProductEntity {

    private String orderProductName;
    private Integer orderProductBasePrice;
    private Integer orderProductTotalCount;
    @Setter private Integer orderProductTotalAmount;
    private List<OrderProductOptionEntity> orderProductOptions;

    @Builder
    private OrderProductEntity(
        String orderProductName,
        List<OrderProductOptionEntity> orderProductOptions,
        Integer orderProductBasePrice,
        Integer orderProductTotalCount,
        Integer orderProductTotalAmount
    ) {
        this.orderProductName = orderProductName;
        this.orderProductBasePrice = orderProductBasePrice;
        this.orderProductTotalCount = orderProductTotalCount;
        this.orderProductTotalAmount = orderProductTotalAmount;

        if (!CollectionUtils.isEmpty(orderProductOptions)) {
            this.orderProductOptions = new ArrayList<>();
            this.orderProductOptions.addAll(orderProductOptions);
        }
    }

}
