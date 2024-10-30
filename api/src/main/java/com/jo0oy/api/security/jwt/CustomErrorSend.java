package com.jo0oy.api.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.global.error.ErrorCodeIfs;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomErrorSend {

    public static void handleException(HttpServletResponse response, ErrorCodeIfs errorCode) throws IOException {
        Api<Object> apiResponse = Api.ERROR(errorCode);
        response.setStatus(errorCode.getHttpStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }
}
