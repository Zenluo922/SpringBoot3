package com.powernode.springboot;

import com.powernode.springboot.bean.AppBean;
import com.powernode.springboot.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({User.class, AppBean.class})
//@ConfigurationPropertiesScan(basePackages = "com.powernode.springboot.bean")
@SpringBootApplication
public class Springboot308ConfigBindToBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot308ConfigBindToBeanApplication.class, args);
    }

}
