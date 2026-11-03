package com.powernode.springboot.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;


public interface UserService {
    void save(Long id, String name);
    void deleteById(Long id);
}
