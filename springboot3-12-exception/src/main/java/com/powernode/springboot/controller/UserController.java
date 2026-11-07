package com.powernode.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/resource/{id}")
    public String getResource(@PathVariable("id") Long id ){
        if (id == 1){
            throw new IllegalArgumentException("无效ID"+id);
        }
        return "ID="+id;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException e){
        return "错误信息:"+e.getMessage();
    }
}
