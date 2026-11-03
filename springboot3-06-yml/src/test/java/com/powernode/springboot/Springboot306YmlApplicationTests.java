package com.powernode.springboot;

import com.powernode.springboot.service.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot306YmlApplicationTests {

    @Autowired
    private SystemService systemService;
    @Test
    void contextLoads() {
        systemService.print();
    }

}
