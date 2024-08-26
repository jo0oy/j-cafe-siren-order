package com.jo0oy.api.product.converter;

import com.jo0oy.api.global.annotation.Converter;
import com.jo0oy.api.product.dto.request.ProductOptionGroupRequest;
import com.jo0oy.api.product.dto.request.ProductOptionRequest;
import com.jo0oy.api.product.dto.request.ProductRequest;
import com.jo0oy.api.product.dto.response.ProductOptionGroupResponse;
import com.jo0oy.api.product.dto.response.ProductOptionResponse;
import com.jo0oy.api.product.dto.response.ProductResponse;
import com.jo0oy.db.product.mongo.ProductEntity;
import com.jo0oy.db.product.mongo.ProductOptionEntity;
import com.jo0oy.db.product.mongo.ProductOptionGroupEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Converter
public class ProductConverter {

    public ProductEntity toEntity(ProductRequest request) {

        // ProductOptionGroupRequest --> ProductOptionGroupEntity 변환
        List<ProductOptionGroupEntity> productOptionGroupEntities = null;
        if (!CollectionUtils.isEmpty(request.productOptionGroups())) {
            productOptionGroupEntities
                = request.productOptionGroups().stream()
                .map(this::toEntity)
                .toList();
        }

        return ProductEntity.builder()
            .productName(request.productName())
            .productPrice(request.productPrice())
            .description(request.description())
            .productOptionGroups(productOptionGroupEntities)
            .build();
    }

    public ProductOptionGroupEntity toEntity(ProductOptionGroupRequest request) {

        // ProductOptionRequest --> ProductOptionEntity 변환
        List<ProductOptionEntity> productOptionEntities = null;
        if (!CollectionUtils.isEmpty(request.productOptions())) {
            productOptionEntities
                = request.productOptions()
                .stream()
                .map(this::toEntity)
                .toList();
        }

        return ProductOptionGroupEntity.builder()
            .productOptionGroupName(request.productOptionGroupName())
            .productOptions(productOptionEntities)
            .build();
    }

    public ProductOptionEntity toEntity(ProductOptionRequest request) {
        return ProductOptionEntity.builder()
            .productOptionName(request.productOptionName())
            .productOptionUnitPrice(request.productOptionUnitPrice())
            .defaultCount(
                Objects.nonNull(request.defaultCount()) ? request.defaultCount() : null
            )
            .maxCount(
                Objects.nonNull(request.maxCount()) ? request.maxCount() : null
            )
            .build();
    }

    public ProductResponse toResponse(ProductEntity entity) {

        // ProductOptionGroupEntity --> ProductOptionGroupResponse 변환
        List<ProductOptionGroupResponse> productOptionGroupResponses = null;
        if (!CollectionUtils.isEmpty(entity.getProductOptionGroups())) {
            productOptionGroupResponses
                = entity.getProductOptionGroups()
                .stream()
                .map(this::toResponse)
                .toList();
        }

        return ProductResponse.builder()
            .id(entity.getId())
            .productName(entity.getProductName())
            .productPrice(entity.getProductPrice())
            .description(entity.getDescription())
            .productOptionGroups(productOptionGroupResponses)
            .build();
    }

    public ProductOptionGroupResponse toResponse(ProductOptionGroupEntity entity) {

        // ProductOptionEntity --> ProductOptionResponse 변환
        List<ProductOptionResponse> productOptionResponses = null;
        if (!CollectionUtils.isEmpty(entity.getProductOptions())) {
            productOptionResponses
                = entity.getProductOptions()
                .stream()
                .map(this::toResponse)
                .toList();
        }

        return ProductOptionGroupResponse.builder()
            .productOptionGroupName(entity.getProductOptionGroupName())
            .productOptions(productOptionResponses)
            .build();
    }

    public ProductOptionResponse toResponse(ProductOptionEntity entity) {
        return ProductOptionResponse.builder()
            .productOptionName(entity.getProductOptionName())
            .productOptionUnitPrice(entity.getProductOptionUnitPrice())
            .defaultCount(entity.getDefaultCount())
            .maxCount(entity.getMaxCount())
            .build();
    }
}
