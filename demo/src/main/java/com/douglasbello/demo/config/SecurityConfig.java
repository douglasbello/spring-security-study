package com.douglasbello.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.csrf().disable()
	    .authorizeHttpRequests()
	    .requestMatchers(HttpMethod.GET, "/free")
	    .permitAll()
	    .anyRequest().authenticated().and().cors();

	http.addFilterBefore(new Filter(), UsernamePasswordAuthenticationFilter.class);
	return http.build();
    }
}
