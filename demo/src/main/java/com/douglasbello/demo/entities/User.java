package com.douglasbello.demo.entities;

public class User {
    private String username;
    private String password;
    private String name;

    public User() {
    }

    public User(String username,String password) {
	this.username = username;
	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public String getName() {
	return name;
    }

    public void setUsername(String username) {
	this.username = username;
    }


    public void setPassword(String password) {
	this.password = password;
    }


    public void setName(String name) {
	this.name = name;
    }
}
