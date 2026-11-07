package com.powernode.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.powernode.transaction.repository")
@SpringBootApplication
public class Springboot314TransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot314TransactionApplication.class, args);
    }

}
