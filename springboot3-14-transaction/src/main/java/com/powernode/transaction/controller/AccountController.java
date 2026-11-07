package com.powernode.transaction.controller;

import com.powernode.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/transfer")
    public String transfer(String fromActNo, String toActNo, double money, Model model) {
        try {
            accountService.transfer(fromActNo, toActNo, money);
            return "success";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
