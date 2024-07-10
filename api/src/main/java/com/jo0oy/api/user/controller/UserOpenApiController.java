package com.jo0oy.api.user.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.user.business.UserBusiness;
import com.jo0oy.api.user.dto.request.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/open-api/users")
@RestController
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping("")
    public ResponseEntity<Api<Void>> register(
        @Valid @RequestBody UserRequest request
    ) {
        userBusiness.register(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build();
    }
}
