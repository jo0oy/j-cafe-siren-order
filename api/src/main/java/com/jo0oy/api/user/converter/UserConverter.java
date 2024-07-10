package com.jo0oy.api.user.converter;

import com.jo0oy.api.global.annotation.Converter;
import com.jo0oy.api.global.error.ErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.api.user.dto.request.UserRequest;
import com.jo0oy.api.user.dto.response.UserResponse;
import com.jo0oy.db.user.UserEntity;

import java.util.Optional;

@Converter
public class UserConverter {

    public UserEntity toEntity(UserRequest request) {
        return Optional.ofNullable(request)
            .map(it ->
                UserEntity.builder()
                    .username(request.username())
                    .password(request.password())
                    .phoneNumber(request.phoneNumber())
                    .build()
            ).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
            .map(it ->
                UserResponse.builder()
                    .userId(userEntity.getId())
                    .username(userEntity.getUsername())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .build()
            ).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
