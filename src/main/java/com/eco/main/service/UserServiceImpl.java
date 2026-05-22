package com.eco.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eco.main.entity.User;

import com.eco.main.reposotory.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public String userRegister(User user) 
	{
		
       int id=userRepo.save(user).getId();
		
		return "Admin Register Successfull with id: "+id;
	}

	@Override
	public User userLogin(String email, String pass) 
	{
		User validUser=userRepo.findByEmail(email);
		 
		if(validUser != null && validUser.getPass().equals(pass))
		{
			return validUser;
		}
		else
		{
		return null;
		}
		
		
	}
	

}
