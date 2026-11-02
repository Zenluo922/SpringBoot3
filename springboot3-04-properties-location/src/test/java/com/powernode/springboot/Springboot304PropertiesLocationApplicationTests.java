package com.powernode.springboot;

import com.powernode.springboot.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot304PropertiesLocationApplicationTests {

    @Autowired
    private AppConfig appConfig;

    @Test
    void test01() {
        appConfig.printInfo();
    }

}
