package com.powernode.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/Hello")
    public String hello() {
        return "hello World";
    }
}
