package com.jo0oy.api.auth.controller;

import com.jo0oy.api.auth.business.AuthBusiness;
import com.jo0oy.api.auth.dto.request.AuthLoginRequest;
import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.security.jwt.AuthTokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthApiController {

    private final AuthBusiness authBusiness;

    @PostMapping("/api/login")
    public ResponseEntity<Api<AuthTokenResponse>> login(
        @Valid @RequestBody AuthLoginRequest request
    ) {
        var data = authBusiness.login(request);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }
}
