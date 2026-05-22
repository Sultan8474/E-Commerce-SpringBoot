package com.eco.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eco.main.entity.Admin;
import com.eco.main.reposotory.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService 
{
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public String  adminRegister(Admin admin) 
	{
		int id=adminRepo.save(admin).getId();
		
		return "Admin Register Successfull with id: "+id;
	}

	@Override
	public Admin adminLogin(String email, String pass) 
	{
		Admin validAdmin=adminRepo.findByEmail(email);
		 
		if(validAdmin != null && validAdmin.getPass().equals(pass))
		{
			return validAdmin;
		}
		else
		{
		return null;
		}
		
		
	}

}
