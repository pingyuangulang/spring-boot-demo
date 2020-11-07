package com.five.monkey.shard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.five.monkey.dal.mapper"})
public class ShardJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardJdbcApplication.class, args);
    }

}
