package com.powernode.springboot;

import com.powernode.springboot.bean.Address;
import com.powernode.springboot.bean.User;
import com.powernode.springboot.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot308ConfigBindToBeanApplicationTests {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private User user;
    @Autowired
    private Address address;
    @Test
    void contextLoads() {
        System.out.println(appConfig);
    }
    @Test
    void appConfig() {
        System.out.println(user);
    }

    @Test
    void address() {
        System.out.println(address);
    }
}
