package com.softz.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    // SYSTEM MESSAGE
    UNCATEGORIZED_EXCEPTION(101999, "Uncategorized exception",  HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(101001, "Invalid key", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(101002, "Un authenticated", HttpStatus.UNAUTHORIZED);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final String message;
    private final int code;
    private final HttpStatusCode statusCode;
}
