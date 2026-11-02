package com.powernode.springboot;

import com.powernode.springboot.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Springboot304PropertiesLocationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Springboot304PropertiesLocationApplication.class, args);

        AppConfig appConfig = applicationContext.getBean(AppConfig.class);
        appConfig.printInfo();

        applicationContext.close();
    }

}
