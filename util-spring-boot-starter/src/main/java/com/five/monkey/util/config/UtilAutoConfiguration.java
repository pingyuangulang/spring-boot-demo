package com.five.monkey.util.config;

import com.five.monkey.util.server.StringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jim
 * @date 2020/12/22 20:57
 */
@Configuration
public class UtilAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({StringUtil.class})
    public StringUtil stringUtil() {
        return new StringUtil();
    }
}
