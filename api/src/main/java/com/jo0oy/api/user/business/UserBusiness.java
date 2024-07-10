package com.jo0oy.api.user.business;

import com.jo0oy.api.global.annotation.Business;
import com.jo0oy.api.user.converter.UserConverter;
import com.jo0oy.api.user.dto.request.UserRequest;
import com.jo0oy.api.user.dto.response.UserResponse;
import com.jo0oy.api.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    public void register(UserRequest request) {
        userService.save(
            userConverter.toEntity(request)
        );
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUser(userId);

        return userConverter.toResponse(userEntity);
    }
}
