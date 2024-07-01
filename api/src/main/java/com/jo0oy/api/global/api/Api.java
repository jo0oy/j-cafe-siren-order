package com.jo0oy.api.global.api;


import com.jo0oy.api.global.error.ErrorCode;
import com.jo0oy.api.global.error.ErrorCodeIfs;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Api<T> {

    private boolean success;
    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;
    private T data;

    public static <T> Api<T> OK(T data) {
        var code = ErrorCode.OK;

        return Api.<T>builder()
            .success(true)
            .resultCode(code.getErrorCode())
            .resultMessage(code.getDescription())
            .resultDescription("성공")
            .data(data)
            .build();
    }

    public static <T> Api<T> OK() {
        return OK(null);
    }

    public static <T> Api<T> ERROR(ErrorCodeIfs errorCodeIfs) {
        return Api.<T>builder()
            .success(false)
            .resultCode(errorCodeIfs.getErrorCode())
            .resultMessage(errorCodeIfs.getDescription())
            .resultDescription("에러 발생")
            .data(null)
            .build();
    }

    public static <T> Api<T> ERROR(ErrorCodeIfs errorCodeIfs, String description) {
        return Api.<T>builder()
            .success(false)
            .resultCode(errorCodeIfs.getErrorCode())
            .resultMessage(errorCodeIfs.getDescription())
            .resultDescription(description)
            .data(null)
            .build();
    }
}
