package com.five.monkey.util.property;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jim
 * @date 2020/12/23 9:41
 */
@ConfigurationProperties(prefix = "spring.util.http")
public class HttpProperty {

    /* 最大连接数 */
    private Integer maxTotal = 100;

    /* 并发数 */
    private Integer defaultMaxPerRoute = 20;

    /* 创建连接的最长时间,单位:ms */
    private Integer connectTimeout = 1000;

    /* 从连接池中获取到连接的最长时间,单位:ms */
    private Integer connectionRequestTimeout = 500;

    /* 数据传输的最长时间,单位:ms */
    private Integer socketTimeout = 10000;

    /* 测试者名称 */
    private String name = "jim";

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
