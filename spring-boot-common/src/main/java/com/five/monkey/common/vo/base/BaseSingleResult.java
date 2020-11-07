package com.five.monkey.common.vo.base;

/**
 * @author jim
 * @date 2020/10/28 16:47
 */
public class BaseSingleResult<T> {

    private int code;

    private String message;

    private T data;

    public BaseSingleResult() {

    }

    public BaseSingleResult(T data) {
        this(200, "SUCCESS", data);
    }

    public BaseSingleResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
