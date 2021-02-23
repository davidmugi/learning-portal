package com.learning.portal.core.config.handles;

public class CustomException extends Exception{

    private int code;

    private String message;


    public CustomException(int code, String message, Throwable cause) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
