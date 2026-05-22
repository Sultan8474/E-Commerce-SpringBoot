package com.eco.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService 
{

	    @Autowired
	    private JavaMailSender mailSender;

	    public void sendEmail(String toEmail)
	    {
	        SimpleMailMessage message = new SimpleMailMessage();

	        message.setTo(toEmail);
	        message.setSubject("Order Shipped 🚚");
	        message.setText("Your order has been shipped successfully. Thank you for shopping with us!");

	        mailSender.send(message);

	        System.out.println("Email sent successfully!");
	    }
	

}
