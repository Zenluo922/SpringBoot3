package com.powernode.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class OtherController {
    @GetMapping("/other/{id}")
    public String getResource(@PathVariable("id")Long id){
        if (id == 1){
            throw new IllegalArgumentException("无效ID"+id);
        }
        return "ID="+id;
    }
}
