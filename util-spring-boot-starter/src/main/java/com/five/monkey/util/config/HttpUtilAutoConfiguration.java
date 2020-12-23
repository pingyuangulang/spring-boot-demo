package com.five.monkey.util.config;

import com.five.monkey.util.server.HttpUtil;
import com.five.monkey.util.property.HttpProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jim
 * @date 2020/12/23 10:08
 */
@Configuration
@EnableConfigurationProperties({HttpProperty.class})
public class HttpUtilAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({HttpProperty.class})
    public HttpUtil httpUtil() {
        return new HttpUtil();
    }

    @Bean
    @ConditionalOnBean({HttpProperty.class})
    public HttpUtil httpUtil(HttpProperty httpProperty) {
        return new HttpUtil(httpProperty);
    }
}
