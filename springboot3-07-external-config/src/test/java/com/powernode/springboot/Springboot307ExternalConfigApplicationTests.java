package com.powernode.springboot;

import com.powernode.springboot.config.AppConfig;
import com.powernode.springboot.config.ProfilesActive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class

Springboot307ExternalConfigApplicationTests {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private ProfilesActive profilesActive;
    @Test
    void contextLoads() {
        appConfig.print();
    }

    @Test
    void profilesActive() {
        profilesActive.print();
    }

}
