package com.jo0oy.api.order.converter;

import com.jo0oy.api.global.annotation.Converter;
import com.jo0oy.api.order.dto.request.OrderProductOptionRequest;
import com.jo0oy.api.order.dto.request.OrderProductRequest;
import com.jo0oy.api.order.dto.request.OrderRequest;
import com.jo0oy.api.order.dto.response.OrderProductOptionResponse;
import com.jo0oy.api.order.dto.response.OrderProductResponse;
import com.jo0oy.api.order.dto.response.OrderResponse;
import com.jo0oy.db.order.mongo.OrderEntity;
import com.jo0oy.db.order.mongo.OrderProductEntity;
import com.jo0oy.db.order.mongo.OrderProductOptionEntity;
import com.jo0oy.db.order.mongo.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class OrderConverter {

    public OrderEntity toEntity(OrderRequest request) {

        // OrderProductRequest --> OrderProductEntity 변환
        List<OrderProductEntity> orderProductEntities = null;
        if (!CollectionUtils.isEmpty(request.orderProducts())) {
            orderProductEntities = request.orderProducts()
                .stream()
                .map(this::toEntity)
                .toList();
        }

        return OrderEntity.builder()
            .userId(request.userId())
            .storeId(request.storeId())
            .storeName(request.storeName())
            .totalCount(request.totalCount())
            .totalAmount(request.totalAmount())
            .orderType(OrderType.valueOf(request.orderType()))
            .requestComment(request.requestComment())
            .orderStatus(
                Objects.nonNull(request.orderStatus()) ? request.orderStatus() : null
            )
            .orderedAt(
                Objects.nonNull(request.orderedAt()) ? request.orderedAt() : null
            )
            .lastModifiedAt(
                Objects.nonNull(request.lastModifiedAt()) ? request.lastModifiedAt() : null
            )
            .orderProducts(orderProductEntities)
            .build();
    }

    public OrderProductEntity toEntity(OrderProductRequest request) {

        // OrderProductOptionRequest --> OrderProductOptionEntity 변환
        List<OrderProductOptionEntity> orderProductOptionEntities = null;
        if (!CollectionUtils.isEmpty(request.orderProductOptions())) {
            orderProductOptionEntities = request.orderProductOptions()
                .stream()
                .map(this::toEntity)
                .toList();
        }

        return OrderProductEntity.builder()
            .orderProductName(request.orderProductName())
            .orderProductBasePrice(request.orderProductBasePrice())
            .orderProductTotalCount(request.orderProductTotalCount())
            .orderProductTotalAmount(request.orderProductTotalAmount())
            .orderProductOptions(orderProductOptionEntities)
            .build();
    }

    public OrderProductOptionEntity toEntity(OrderProductOptionRequest request) {
        return OrderProductOptionEntity.builder()
            .orderProductOptionName(request.orderProductOptionName())
            .orderProductOptionTotalCount(request.optionTotalCount())
            .orderProductOptionTotalAmount(request.optionTotalAmount())
            .build();
    }

    public OrderResponse toResponse(OrderEntity entity) {

        List<OrderProductResponse> orderProductResponses = null;
        if (!CollectionUtils.isEmpty(entity.getOrderProducts())) {
            orderProductResponses = entity.getOrderProducts()
                .stream()
                .map(this::toResponse)
                .toList();
        }

        return OrderResponse.builder()
            .orderId(entity.getId())
            .userId(entity.getUserId())
            .storeId(entity.getStoreId())
            .storeName(entity.getStoreName())
            .totalCount(entity.getTotalCount())
            .totalAmount(entity.getTotalAmount())
            .orderType(entity.getOrderType())
            .orderStatus(entity.getOrderStatus())
            .requestComment(entity.getRequestComment())
            .orderProducts(orderProductResponses)
            .orderedAt(entity.getOrderedAt())
            .build();
    }

    public OrderProductResponse toResponse(OrderProductEntity entity) {

        List<OrderProductOptionResponse> orderProductOptionResponses = null;
        if (!CollectionUtils.isEmpty(entity.getOrderProductOptions())) {
            orderProductOptionResponses = entity.getOrderProductOptions()
                .stream()
                .map(this::toResponse)
                .toList();
        }

        return OrderProductResponse.builder()
            .orderProductName(entity.getOrderProductName())
            .orderProductBasePrice(entity.getOrderProductBasePrice())
            .orderProductTotalCount(entity.getOrderProductTotalCount())
            .orderProductTotalAmount(entity.getOrderProductTotalAmount())
            .orderProductOptions(orderProductOptionResponses)
            .build();
    }

    public OrderProductOptionResponse toResponse(OrderProductOptionEntity entity) {
        return OrderProductOptionResponse.builder()
            .optionName(entity.getOrderProductOptionName())
            .optionTotalCount(entity.getOrderProductOptionTotalCount())
            .optionTotalAmount(entity.getOrderProductOptionTotalAmount())
            .build();
    }
}
