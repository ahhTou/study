package com.ahhtou.exception;

public class ReturnException extends Exception {

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public ReturnException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
