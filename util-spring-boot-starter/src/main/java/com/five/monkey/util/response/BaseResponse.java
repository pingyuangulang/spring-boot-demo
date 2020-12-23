package com.five.monkey.util.response;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;

/**
 * @author jim
 * @date 2020/12/23 11:30
 */
public class BaseResponse {

    /* 响应头信息 */
    private Map<String, String> header;
    /* 响应状态码 */
    private int status;
    /* 响应json数据 */
    private String data;

    private BaseResponse() {
        this(null, -1, StringUtils.EMPTY);
    }

    private BaseResponse(Map<String, String> header, int status, String data) {
        this.header = header;
        this.status = status;
        this.data = data;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public int getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public static class BaseResponseBuilder {
        private Map<String, String> header;
        private int status;
        private String data;

        public static BaseResponseBuilder create() {
            return new BaseResponseBuilder();
        }

        public BaseResponseBuilder setHeader(Map<String, String> header) {
            this.header = header;
            return this;
        }

        public BaseResponseBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public BaseResponseBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public BaseResponse build() {
            return new BaseResponse(this.header, this.status, this.data);
        }
    }
}
