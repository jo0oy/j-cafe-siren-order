package com.jo0oy.api.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *  JWT Error 경우 5000번대 에러코드 사용
 */
@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCodeIfs {

    JWT_EXPIRED(HttpStatus.BAD_REQUEST.value(), 5401, "만료된 JWT 토큰입니다."),
    INVALID_JWT(HttpStatus.BAD_REQUEST.value(), 5404, "유효하지 않은 JWT 토큰입니다."),
    UNSUPPORTED_JWT(HttpStatus.BAD_REQUEST.value(), 5403, "지원되지 않는 JWT 토큰입니다."),
    ILLEGAL_JWT(HttpStatus.BAD_REQUEST.value(), 5402, "잘못된 JWT 토큰입니다.");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
