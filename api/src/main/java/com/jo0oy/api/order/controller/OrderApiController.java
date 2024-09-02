package com.jo0oy.api.order.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.order.business.OrderBusiness;
import com.jo0oy.api.order.dto.request.OrderRequest;
import com.jo0oy.api.order.dto.response.OrderResponse;
import com.jo0oy.db.order.mongo.OrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderApiController {

    private final OrderBusiness orderBusiness;

    @PostMapping("")
    public ResponseEntity<Api<Void>> registerOrder(
        @Valid @RequestBody OrderRequest request
    ) {
        orderBusiness.register(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                Api.OK()
            );
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Api<OrderResponse>> changeStatus(
        @PathVariable("orderId") String orderId,
        @Valid @RequestParam("status") OrderStatus orderStatus
    ) {
        var data = orderBusiness.changeOrderStatus(orderId, orderStatus);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Api<OrderResponse>> cancelOrder(
        @PathVariable("orderId") String orderId
    ) {
        var data = orderBusiness.cancel(orderId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Api<OrderResponse>> getOrderById(
        @PathVariable("orderId") String orderId
    ) {
        var data = orderBusiness.getOrderById(orderId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }

    @GetMapping("")
    public ResponseEntity<Api<List<OrderResponse>>> getOrderList() {
        var data = orderBusiness.getOrders();

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }

}
