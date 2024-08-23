package com.jo0oy.api.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Menu Error 경우 2000번대 에러코드 사용
 */
@RequiredArgsConstructor
@Getter
public enum MenuErrorCode implements ErrorCodeIfs {

    MENU_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 2404, "메뉴를 찾을 수 없음");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
