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
    public Integer age;

    public void printInfo(){
        System.out.println(username);
        System.out.println(email);
        System.out.println(age);
    }
}
