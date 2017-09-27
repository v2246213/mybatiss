package com.ctmp01.web.common.valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yipingdong on 2017/4/21.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
    private String resource;
    private String field;
    private String code;
    private String message;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
