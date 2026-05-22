package com.eco.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.eco.main.entity.Admin;
import com.eco.main.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/openAdmin")
	public String openAdminRegistrationPage(Model model)
	{
		model.addAttribute("adm", new Admin());
		
		return "adminRegister";
	}
	
	@PostMapping("/regForm")
	public String adminRegister(@ModelAttribute("adm")   Admin admin ,Model model)
	{
		
		String res=adminService.adminRegister(admin);
		model.addAttribute("resMsg", res);
		
		return "adminLogin";
	}
	
	@GetMapping("/adminLogin")
	public String openLoginPage(Model model)
	{
		model.addAttribute("admin", new Admin());
		
		return "adminlogin";
	}
	
	@PostMapping("/loginForm")
	public String adminLogin(@ModelAttribute("admin") Admin admin,
	                         Model model,
	                         HttpSession session)
	{
	    Admin validAdmin = adminService.adminLogin(
	            admin.getEmail(),
	            admin.getPass());

	    if (validAdmin != null)
	    {
	        // Save admin session
	        session.setAttribute("loggedInAdmin", validAdmin);
	        session.setAttribute("adminEmail", validAdmin.getEmail());

	        return "redirect:/adminDashbord";
	    }
	    else
	    {
	        model.addAttribute("errMsg", "Wrong Password");
	        return "adminLogin";
	    }
	}
	

}
