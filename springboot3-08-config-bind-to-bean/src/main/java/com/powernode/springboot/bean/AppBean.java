package com.powernode.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app2.abc")
public class AppBean {
    private String[] name;

}
