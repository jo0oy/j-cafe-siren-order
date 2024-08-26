package com.jo0oy.api.product.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.product.business.ProductBusiness;
import com.jo0oy.api.product.dto.request.ProductRequest;
import com.jo0oy.api.product.dto.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductApiController {

    private final ProductBusiness productBusiness;

    @PostMapping("")
    public ResponseEntity<Api<Void>> registerProduct(
        @Valid @RequestBody ProductRequest request
    ) {
        productBusiness.register(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                Api.OK()
            );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Api<ProductResponse>> updateProduct(
        @PathVariable("productId") String productId,
        @Valid @RequestBody ProductRequest request
    ) {
        var data = productBusiness.update(productId, request);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Api<Void>> deleteProduct(
        @PathVariable("productId") String productId
    ) {
        productBusiness.delete(productId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK()
            );
    }
}
