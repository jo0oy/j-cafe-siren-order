package com.jo0oy.api.user.business;

import com.jo0oy.api.global.annotation.Business;
import com.jo0oy.api.user.converter.UserConverter;
import com.jo0oy.api.user.dto.request.UserRequest;
import com.jo0oy.api.user.dto.response.UserResponse;
import com.jo0oy.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRequest request) {
        var userEntity = userConverter.toEntity(request);
        userEntity.setEncodedPassword(passwordEncoder.encode(request.password()));

        userService.save(userEntity);
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUser(userId);

        return userConverter.toResponse(userEntity);
    }
}
