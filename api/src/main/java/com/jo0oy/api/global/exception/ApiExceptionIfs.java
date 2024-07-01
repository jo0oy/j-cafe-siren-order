package com.jo0oy.api.global.exception;


import com.jo0oy.api.global.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
