package com.ctmp01.web.common.valid;

import org.springframework.validation.Errors;

/**
 * Created by yipingdong on 2017/4/21.
 */
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
