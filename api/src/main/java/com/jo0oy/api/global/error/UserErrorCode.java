package com.jo0oy.api.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * User Error 경우 1000번대 에러코드 사용
 */
@RequiredArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 1404, "회원을 찾을 수 없음");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
