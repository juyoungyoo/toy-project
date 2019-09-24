package com.toy.shoppingmall.error;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
class ErrorResponse {

    private final String code;
    private final String message;
    private List<FieldError> errors;

    @Builder
    ErrorResponse(final String code, final String message, final List<FieldError> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    ErrorResponse(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
