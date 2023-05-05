package com.douglasbello.demo.config;

import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class MyToken {

    public static Authentication decodeToken(HttpServletRequest request) {
	if (request.getHeader("Authorization").equals("Bearer 12345")) {
	    return new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList());
	}

	return null;
    }
}

