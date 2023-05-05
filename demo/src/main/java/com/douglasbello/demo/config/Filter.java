package com.douglasbello.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class Filter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	
	if (request.getHeader("Authorization") != null) {
	    Authentication auth = MyToken.decodeToken(request);

	    if (auth != null) {
		SecurityContextHolder.getContext().setAuthentication(auth);
	    }
	    else {
		RequestErrorDTO errorDTO = new RequestErrorDTO(401, "User not authorized for this system");
		response.setStatus(errorDTO.getStatus());
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().print(mapper.writeValueAsString(errorDTO));
		response.getWriter().flush();
		return;
	    }
	}

	filterChain.doFilter(request, response);
    }
}
