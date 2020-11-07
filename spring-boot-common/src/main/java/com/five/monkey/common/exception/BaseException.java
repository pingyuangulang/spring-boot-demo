package com.five.monkey.common.exception;

/**
 * @author jim
 * @date 2020/10/28 16:56
 */
public class BaseException extends Exception {

    private int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
