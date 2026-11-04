package com.powernode.springboot;

import com.powernode.springboot.bean.Vip;
import com.powernode.springboot.service.VipService;
import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.powernode.springboot.dao")
public class Springboot310MybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Springboot310MybatisApplication.class, args);

        VipService vipService = applicationContext.getBean(VipService.class);

        Vip vip1 = new Vip("jack", "123456789", "1999-10-11");
        vipService.saveVip(vip1);

        Vip vip2 = new Vip("lucy", "123456", "2000-10-10");
        vipService.saveVip(vip2);
        vipService.findAll().forEach(System.out::println);
        applicationContext.close();
    }

}
