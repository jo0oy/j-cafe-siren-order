package com.jo0oy.api.order.service;

import com.jo0oy.api.global.error.OrderErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.db.order.mongo.OrderEntity;
import com.jo0oy.db.order.mongo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public OrderEntity update(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public OrderEntity findById(String orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new ApiException(OrderErrorCode.ORDER_NOT_FOUND));
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }
}
