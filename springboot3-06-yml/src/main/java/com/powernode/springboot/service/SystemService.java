package com.powernode.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class SystemService {

    @Value("${myapp.username}")
    private String username;
    @Value("${myapp.email}")
    private String email;
    @Value("${myapp.age}")
    private Integer age;
    @Value("${myapp.gender}")
    private Boolean gender;

    public void print() {
        System.out.println(username+","+email+","+age+","+(gender?"男":"女"));
    }
}
