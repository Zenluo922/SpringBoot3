package com.powernode.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppConfig {
    @Value("${spring.datasource.username}")
    private String mysqlUsername;
    @Value("${spring.datasource.password}")
    private String mysqlPassword;
    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private String redisPort;

    public void print(){
        System.out.println(mysqlUsername+","+mysqlPassword+","+redisHost+","+redisPort);
    }
}
