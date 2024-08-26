package com.jo0oy.api.product.service;

import com.jo0oy.api.global.error.ProductErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.db.product.mongo.ProductEntity;
import com.jo0oy.db.product.mongo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void save(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    public ProductEntity findById(String productId) {
        return productRepository.findById(productId)
            .orElseThrow(
                () -> new ApiException(ProductErrorCode.PRODUCT_ERROR_CODE)
            );
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public ProductEntity update(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void delete(String productId) {
        var deleteEntity = findById(productId);
        productRepository.delete(deleteEntity);
    }
}
