package com.eco.main.service;

import com.eco.main.entity.Admin;

public interface AdminService
{
	public String adminRegister(Admin admin);
	
	public Admin adminLogin(String email,String pass);

}
