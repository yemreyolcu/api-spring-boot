package com.example.springbootapi.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/hellospringsecurity")
    public String hellospringsecurity() {
        return "hellospringsecurity";
    }
}
