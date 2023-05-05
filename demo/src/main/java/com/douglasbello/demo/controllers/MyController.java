package com.douglasbello.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @GetMapping(value = "/free")
    public String free() {
	return "this is a free endpoint";
    }

    @GetMapping(value = "/auth")
    public String auth() {
	return "this is a auth endpoint";
    }
}
