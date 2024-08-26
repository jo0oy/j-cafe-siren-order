package com.jo0oy.api.product.business;

import com.jo0oy.api.global.annotation.Business;
import com.jo0oy.api.product.converter.ProductConverter;
import com.jo0oy.api.product.dto.request.ProductRequest;
import com.jo0oy.api.product.dto.response.ProductResponse;
import com.jo0oy.api.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class ProductBusiness {

    private final ProductService productService;
    private final ProductConverter productConverter;

    public void register(ProductRequest request) {
        var menuEntity = productConverter.toEntity(request);
        productService.save(menuEntity);
    }

    public ProductResponse getProductById(String menuId) {
        var menuEntity = productService.findById(menuId);
        return productConverter.toResponse(menuEntity);
    }

    public List<ProductResponse> getProductList() {
        return productService.findAll()
            .stream()
            .map(productConverter::toResponse)
            .collect(Collectors.toList());
    }

    public ProductResponse update(String productId, ProductRequest request) {
        var updateRequestEntity = productConverter.toEntity(request);
        updateRequestEntity.setId(productId);

        var updatedEntity = productService.update(updateRequestEntity);

        return productConverter.toResponse(updatedEntity);
    }

    public void delete(String productId) {
        productService.delete(productId);
    }
}
