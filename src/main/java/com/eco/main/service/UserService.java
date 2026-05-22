package com.eco.main.service;

import com.eco.main.entity.User;

public interface UserService 
{
	 public String userRegister(User user);
		
	public User userLogin(String email,String pass);

}
