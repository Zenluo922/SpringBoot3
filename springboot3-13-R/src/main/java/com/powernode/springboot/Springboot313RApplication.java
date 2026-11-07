package com.powernode.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.powernode.springboot.dao")
public class Springboot313RApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot313RApplication.class, args);
    }

}
