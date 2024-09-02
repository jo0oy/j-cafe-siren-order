package com.jo0oy.api.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderProductOptionResponse(
    String optionName,
    Integer optionTotalCount,
    Integer optionTotalAmount
) {
}
