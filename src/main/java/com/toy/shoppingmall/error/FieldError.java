package com.toy.shoppingmall.error;


import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Getter
class FieldError {

    private static final String BLANK = "";

    private final String field;
    @Nullable
    private final String value;
    private final String reason;

    @Builder
    private FieldError(final String field,
                       @Nullable final String value,
                       final String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    static FieldError of(org.springframework.validation.FieldError fieldError) {
        return FieldError.builder()
                .field(fieldError.getField())
                .value(parseValue(fieldError))
                .reason(fieldError.getDefaultMessage())
                .build();
    }

    private static String parseValue(org.springframework.validation.FieldError fieldError) {
        if (Objects.nonNull(fieldError.getRejectedValue())) {
            return BLANK;
        }
        return fieldError.getRejectedValue().toString();
    }
}