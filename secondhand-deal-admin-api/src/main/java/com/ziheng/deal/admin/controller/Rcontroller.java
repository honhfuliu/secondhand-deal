package com.ziheng.deal.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rcontroller {
    @GetMapping("l/aa")
    public String aa() {
//        int a = 10/0;
        return "aaa";
    }
}
