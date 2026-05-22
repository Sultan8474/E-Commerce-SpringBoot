package com.eco.main.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eco.main.service.EmailService;



@RestController
public class MailController 
{

	    @Autowired
	    private EmailService emailService;

	    @GetMapping("/sendmail")
	    public String sendMail()
	    {
	        emailService.sendEmail("receiver@gmail.com");
	        return "Email Sent Successfully";
	    }
	

}
