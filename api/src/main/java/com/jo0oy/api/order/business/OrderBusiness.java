package com.jo0oy.api.order.business;

import com.jo0oy.api.global.annotation.Business;
import com.jo0oy.api.global.error.OrderErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.api.order.converter.OrderConverter;
import com.jo0oy.api.order.dto.request.OrderProductOptionRequest;
import com.jo0oy.api.order.dto.request.OrderProductRequest;
import com.jo0oy.api.order.dto.request.OrderRequest;
import com.jo0oy.api.order.dto.response.OrderResponse;
import com.jo0oy.api.order.service.OrderService;
import com.jo0oy.db.order.mongo.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Business
public class OrderBusiness {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    public void register(OrderRequest request) {
        // validateOrderProductTotalAmount - 주문 상품별 총 금액 검증
        request.orderProducts().forEach(this::validateOrderProductTotalAmount);

        // validateOrderTotalCount - 총 주문수량 검증
        validateOrderTotalCount(request);

        // validateOrderTotalAmount - 총 주문금액 검증
        validateOrderTotalAmount(request);

        // 주문 엔티티로 변환
        var orderEntity = orderConverter.toEntity(request);

        // 주문 엔티티에 주문상태, 주문일시 필드 setting
        orderEntity.setOrderStatus(OrderStatus.ORDER_COMPLETE);
        orderEntity.setOrderedAt(LocalDateTime.now());

        // 주문 엔티티 저장
        orderService.save(orderEntity);
    }

    public OrderResponse getOrderById(String orderId) {
        var orderEntity = orderService.findById(orderId);
        return orderConverter.toResponse(orderEntity);
    }

    public List<OrderResponse> getOrders() {
        return orderService.findAll()
            .stream()
            .map(orderConverter::toResponse)
            .collect(Collectors.toList());
    }

    public OrderResponse changeOrderStatus(String orderId, OrderStatus orderStatus) {
        var targetEntity = orderService.findById(orderId);
        targetEntity.setOrderStatus(orderStatus);

        var updatedEntity = orderService.update(targetEntity);

        return orderConverter.toResponse(updatedEntity);
    }

    public OrderResponse cancel(String orderId) {
        return this.changeOrderStatus(orderId, OrderStatus.CANCEL);
    }


    /**
        ** 주문 관련 검증 로직 **
     */

    // 주문상품의 옵션 (OrderProductOption) 총 금액 검증 로직
    private void validateOrderProductOptionTotalAmount(OrderProductOptionRequest request) {
        var totalAmount = request.orderProductOptionUnitPrice() * (Objects.nonNull(request.optionTotalCount()) ? request.optionTotalCount() : 1);

        if (totalAmount != request.optionTotalAmount()) {
            throw new ApiException(OrderErrorCode.ORDER_INVALID_VALUE, "주문상품옵션 총 금액 입력값이 잘못되었습니다.");
        }
    }

    // 주문 상품 (OrderProduct) 총 금액 검증 로직
    private void validateOrderProductTotalAmount(OrderProductRequest request) {

        // 옵션별 총 금액 검증
        request.orderProductOptions()
            .forEach(this::validateOrderProductOptionTotalAmount);

        // 모든 옵션 추가 총 금액
        var optionTotalAmount = request.orderProductOptions()
            .stream()
            .mapToInt(OrderProductOptionRequest::optionTotalAmount)
            .sum();

        var totalAmount = (request.orderProductBasePrice() + optionTotalAmount) * request.orderProductTotalCount();
        
        if (totalAmount != request.orderProductTotalAmount()) {
            throw new ApiException(OrderErrorCode.ORDER_INVALID_VALUE, "주문상품 총 금액 입력값이 잘못되었습니다.");
        }
    }

    // 총 주문 수량 검증 로직
    private void validateOrderTotalCount(OrderRequest request) {

        var totalCount = request.orderProducts()
            .stream()
            .mapToInt(OrderProductRequest::orderProductTotalCount)
            .sum();

        if (totalCount != request.totalCount()) {
            throw new ApiException(OrderErrorCode.ORDER_INVALID_VALUE, "총 주문수량 입력값이 잘못되었습니다.");
        }
    }

    // 총 주문 금액 검증 로직
    private void validateOrderTotalAmount(OrderRequest request) {

        var totalAmount = request.orderProducts()
            .stream()
            .mapToInt(OrderProductRequest::orderProductTotalAmount)
            .sum();

        if (totalAmount != request.totalAmount()) {
            throw new ApiException(OrderErrorCode.ORDER_INVALID_VALUE, "총 주문금액 입력값이 잘못되었습니다.");
        }
    }
}
