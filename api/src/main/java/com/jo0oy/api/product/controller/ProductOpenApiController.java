package com.jo0oy.api.product.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.product.business.ProductBusiness;
import com.jo0oy.api.product.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/open-api/products")
@RestController
public class ProductOpenApiController {

    private final ProductBusiness productBusiness;

    @GetMapping("/{productId}")
    public ResponseEntity<Api<ProductResponse>> getProductById(
        @PathVariable("productId") String productId
    ) {
        var data = productBusiness.getProductById(productId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }

    @GetMapping("")
    public ResponseEntity<Api<List<ProductResponse>>> getProductList() {
        var data = productBusiness.getProductList();

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }
}
