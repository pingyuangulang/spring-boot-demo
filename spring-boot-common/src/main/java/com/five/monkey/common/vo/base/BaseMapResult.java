package com.five.monkey.common.vo.base;

import java.util.Map;

/**
 * @author jim
 * @date 2020/10/28 17:41
 */
public class BaseMapResult<K, V> {

    private int code;

    private String message;

    private Map<K, V> data;

    public BaseMapResult() {

    }

    public BaseMapResult(Map<K, V> data) {
        this(200, "SUCCESS", data);
    }

    public BaseMapResult(int code, String message, Map<K, V> data) {
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

    public Map<K, V> getData() {
        return data;
    }

    public void setData(Map<K, V> data) {
        this.data = data;
    }
}
