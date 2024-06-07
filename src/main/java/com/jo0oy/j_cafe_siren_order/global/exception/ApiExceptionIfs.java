package com.jo0oy.j_cafe_siren_order.global.exception;


import com.jo0oy.j_cafe_siren_order.global.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
