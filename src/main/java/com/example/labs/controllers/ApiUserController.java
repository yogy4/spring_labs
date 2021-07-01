package com.example.labs.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiUserController {

    @GetMapping("/login")
    public String api_login(){
        return "api login area";
    }

}
