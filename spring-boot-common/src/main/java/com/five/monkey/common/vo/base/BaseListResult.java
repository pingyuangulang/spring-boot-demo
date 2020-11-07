package com.five.monkey.common.vo.base;

import java.util.List;

/**
 * @author jim
 * @date 2020/10/28 17:33
 */
public class BaseListResult<T> {

    private int code;

    private String message;

    private List<T> data;

    public BaseListResult() {

    }

    public BaseListResult(List<T> data) {
        this(200, "SUCCESS", data);
    }

    public BaseListResult(int code, String message, List<T> data) {
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
