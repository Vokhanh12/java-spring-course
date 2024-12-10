package com.softz.common.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private String[] params;
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode, String... params) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.params = params;
    }
}
