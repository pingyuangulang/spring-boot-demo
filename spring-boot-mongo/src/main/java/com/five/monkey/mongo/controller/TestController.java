package com.five.monkey.mongo.controller;

import com.five.monkey.util.response.BaseResponse;
import com.five.monkey.util.server.HttpUtil;
import com.five.monkey.util.server.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.UUID;

/**
 * @author jim
 * @date 2020/12/23 15:56
 */
@RestController
@RequestMapping("/test/http")
public class TestController {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private StringUtil stringUtil;

    @GetMapping("/do")
    public String doTest() throws IOException {
        return stringUtil.join('-', httpUtil.exeTest("https://www.baidu.com/"), UUID.randomUUID().toString());
    }

    @GetMapping("/doGet")
    public BaseResponse doGet() throws IOException {
        return httpUtil.doGet("https://www.baidu.com/", null);
    }
}
