package com.jo0oy.db.order.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Document("order")
public class OrderEntity {

    @Id private String id;
    private Long userId;                                // 주문자 id
    private Long storeId;                               // 매장 id
    private String storeName;                           // 매장명
    private List<OrderProductEntity> orderProducts;     // 주문메뉴
    @Setter private Integer totalCount;                 // 총 수량
    @Setter private Integer totalAmount;                // 총 금액
    @Setter private OrderStatus orderStatus;            // 주문상태
    private OrderType orderType;                        // 주문타입 (포장/매장)
    private String requestComment;                      // 요청사항
    @Setter private LocalDateTime orderedAt;            // 주문시간
    @Setter private LocalDateTime lastModifiedAt;       // 마지막 수정시간

    @Builder
    private OrderEntity(
        String id,
        Long userId,
        Long storeId,
        String storeName,
        List<OrderProductEntity> orderProducts,
        Integer totalCount,
        Integer totalAmount,
        OrderStatus orderStatus,
        OrderType orderType,
        String requestComment,
        LocalDateTime orderedAt,
        LocalDateTime lastModifiedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.requestComment = requestComment;
        this.orderedAt = orderedAt;
        this.lastModifiedAt = lastModifiedAt;

        if (!CollectionUtils.isEmpty(orderProducts)) {
            this.orderProducts = new ArrayList<>();
            this.orderProducts.addAll(orderProducts);
        }
    }

}
