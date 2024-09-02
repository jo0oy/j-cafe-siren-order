package com.jo0oy.api.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Order Error 경우 3000번대 에러코드 사용
 */
@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCodeIfs {

    ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 3404, "주문을 찾을 수 없음"),
    ORDER_INVALID_VALUE(HttpStatus.BAD_REQUEST.value(), 3406, "주문 정보 입력값이 유효하지 않은 잘못된 값임");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
