package com.five.monkey.util.server;

import com.five.monkey.util.property.HttpProperty;
import com.five.monkey.util.response.BaseResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author jim
 * @date 2020/12/23 9:53
 */
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private CloseableHttpClient httpClient;

    public HttpUtil() {
        this(null);
    }

    public HttpUtil(HttpProperty httpProperty) {
        if (Objects.isNull(httpProperty)) {
            httpClient = HttpClients.createMinimal();
            LOGGER.info("HttpUtil --> default");
        } else {
            // 连接池配置
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(httpProperty.getMaxTotal());
            connectionManager.setDefaultMaxPerRoute(httpProperty.getDefaultMaxPerRoute());
            // request config
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(httpProperty.getSocketTimeout())
                    .setConnectionRequestTimeout(httpProperty.getConnectionRequestTimeout())
                    .setConnectTimeout(httpProperty.getConnectTimeout()).build();
            // http client
            httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            LOGGER.info("HttpUtil --> " + httpProperty.getName());
        }
    }

    public BaseResponse doGet(String uri, Map<String, String> header) throws IOException {
        HttpUriRequest request = new HttpGet(uri);
        setHeader(request, header);
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        return closeableHttpResponse2BaseResponse(httpResponse);
    }

    public BaseResponse doPost(String uri, Map<String, String> header, Map<String, String> requestBody) throws IOException {
        HttpPost request = new HttpPost(uri);
        setHeader(request, header);
        if (Objects.nonNull(requestBody) && !requestBody.isEmpty()) {
            Set<Map.Entry<String, String>> entrySet = requestBody.entrySet();
            List<NameValuePair> parameters = new ArrayList<>();
            HttpEntity httpEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            for (Map.Entry<String, String> entry : entrySet) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            request.setEntity(httpEntity);
        }
        return closeableHttpResponse2BaseResponse(httpClient.execute(request));
    }

    public String exeTest(String uri) throws IOException {
        HttpUriRequest request = new HttpGet(uri);
        httpClient.execute(request);
        LOGGER.info("HttpUtil.exeTest uri=" + uri);
        return "[test success] " + uri;
    }

    /**
     * 向HttpUriRequest{@link org.apache.http.client.methods.HttpUriRequest}设置请求头信息
     */
    private void setHeader(HttpUriRequest request, Map<String, String> header) {
        if (Objects.nonNull(header) && !header.isEmpty()) {
            Set<Map.Entry<String, String>> entrySet = header.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * CloseableHttpResponse{@link org.apache.http.client.methods.CloseableHttpResponse}
     * --> BaseResponse{@link com.five.monkey.util.response.BaseResponse}
     */
    private BaseResponse closeableHttpResponse2BaseResponse(CloseableHttpResponse httpResponse) throws IOException {
        if (Objects.isNull(httpResponse)) {
            return null;
        }
        Map<String, String> headerMap = new HashMap<>();
        Header[] headers = httpResponse.getAllHeaders();
        for (Header header : headers) {
            headerMap.put(header.getName(), header.getValue());
        }
        int status = httpResponse.getStatusLine().getStatusCode();
        String data = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        return BaseResponse.BaseResponseBuilder.create().setHeader(headerMap).setStatus(status).setData(data).build();
    }
}
