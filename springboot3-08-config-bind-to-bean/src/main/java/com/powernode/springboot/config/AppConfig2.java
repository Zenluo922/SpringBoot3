package com.powernode.springboot.config;

import com.powernode.springboot.bean.Address;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {
    @Bean
    @ConfigurationProperties(prefix = "other.abc")
    public Address address() {
        return new Address();
    }
}
