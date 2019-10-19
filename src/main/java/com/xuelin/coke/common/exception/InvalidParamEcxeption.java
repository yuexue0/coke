package com.xuelin.coke.common.exception;

/**
 * 无效的参数异常
 */
public class InvalidParamEcxeption extends Exception {

    public InvalidParamEcxeption() {
        super();
    }

    public InvalidParamEcxeption(String message) {
        super(message);
    }

    public InvalidParamEcxeption(String message, Throwable cause) {
        super(message, cause);
    }

}
