package com.eco.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import com.eco.main.entity.User;

import com.eco.main.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping("/openUser")
	public String openUserRegistrationPage(Model model)
	{
		model.addAttribute("user", new User());
		
		return "userRegister";
	}
	
	
	  @PostMapping("/userRegForm")
	  public String userRegister(@ModelAttribute("user") User user ,Model model) 
	  {
	  
	  String res=userService.userRegister(user); 
	  model.addAttribute("resMsg", res);
	  
	  return "userLogin"; 
	  }
	  
	  
	  
	  @GetMapping("/userLogin")
	  public String openLoginPage(Model model) 
	  {
	  model.addAttribute("user", new User());
	  
	  return "userLogin";
	  }
	  
	  
	  
	  @PostMapping("/userLoginForm")
	  public String userLogin(
	          @ModelAttribute("user") User user,
	          Model model,
	          HttpSession session)
	  {
	      User validUser = userService.userLogin(
	              user.getEmail(),
	              user.getPass()
	      );

	      if (validUser != null)
	      {
	          // Save logged-in user info in session
	          session.setAttribute("userEmail", validUser.getEmail());
	          session.setAttribute("loggedInUser", validUser);

	          return "redirect:/userDashbord";
	      }
	      else
	      {
	          model.addAttribute("errMsg", "Wrong Password");
	          return "userLogin";
	      }
	  }
	  
	  @GetMapping("/logout")
	  public String logout(HttpServletResponse response,
	                       HttpSession session)
	  {
	      // Destroy session
	      session.invalidate();

	      // Delete cookie
	      Cookie cookie = new Cookie("JSESSIONID", null);
	      cookie.setPath("/");
	      cookie.setHttpOnly(true);
	      cookie.setMaxAge(0);
	      response.addCookie(cookie);

	      return "redirect:/";
	  }

}
