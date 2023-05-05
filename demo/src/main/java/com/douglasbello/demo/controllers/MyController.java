package com.douglasbello.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douglasbello.demo.config.AuthToken;
import com.douglasbello.demo.config.MyToken;
import com.douglasbello.demo.entities.User;

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

    @PostMapping(value = "/login")
    public ResponseEntity<AuthToken> login(@RequestBody User user) {
	if (user.getUsername().equals("douglas") && user.getPassword().equals("12345")) {
	    return ResponseEntity.ok(MyToken.encodeToken(user));
	}
	return ResponseEntity.status(403).build();
    }
}
