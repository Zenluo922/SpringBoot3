package com.powernode.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public String handlerIllegalArgumentException(IllegalArgumentException e){
        return "错误信息:"+e.getMessage();
    }
}
