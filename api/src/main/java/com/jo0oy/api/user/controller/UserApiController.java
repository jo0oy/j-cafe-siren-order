package com.jo0oy.api.user.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.user.business.UserBusiness;
import com.jo0oy.api.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/{userId}")
    public ResponseEntity<Api<UserResponse>> accountMe(
        @PathVariable("userId") Long userId
    ) {
        var resultBody = userBusiness.me(userId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(resultBody)
            );
    }
}
