package com.eco.main.Controller;
import java.util.List;

import com.razorpay.RazorpayClient;
import jakarta.servlet.http.HttpSession;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eco.main.entity.Order;
import com.eco.main.entity.User;
import com.eco.main.reposotory.OrderRepository;
import com.eco.main.reposotory.UserRepository;
import com.eco.main.service.EmailService;

@Controller
public class PaymentController
{
    @Autowired
    private OrderRepository orderRepository; // ✅ ADD THIS

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Value("${razorpay.key.id}")
    private String razorpayKey;

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;


    @GetMapping("/checkout")
    public String checkoutPage(
            @RequestParam Double total,
            Model model,
            HttpSession session) throws Exception {

        String email = (String) session.getAttribute("userEmail");

        User user = userRepository.findByEmail(email);

        RazorpayClient client =
                new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject options = new JSONObject();

        options.put("amount", Math.round(total * 100));
        options.put("currency", "INR");
        options.put("receipt", "order_rcptid_11");

        com.razorpay.Order razorpayOrder = client.orders.create(options);

        model.addAttribute("orderId", razorpayOrder.get("id"));
        model.addAttribute("amount", total);
        model.addAttribute("key", razorpayKey);

        model.addAttribute("email", user.getEmail());
        model.addAttribute("phoneNumber", user.getPhone());

        return "payment";
    }

	 
	  @GetMapping("/success")
	  public String paymentSuccess(  @RequestParam String email,
			                        @RequestParam(required = false) Long phoneNumber,
	                               @RequestParam double totalAmount) //@RequestParam String phoneNumber
	  {

	      // ✅ Save Order
	      Order order = new Order();
	      
	      order.setTotalAmount(totalAmount); // 💥 dynamic amount
	      order.setStatus("SUCCESS");
	      order.setEmail(email);
	      order.setPhoneNumber(phoneNumber);
	      
	      orderRepository.save(order);
	      emailService.sendEmail(email);


	      return "success";
	  }
	  
	  @GetMapping("/orders")
	  public String viewOrders(Model model) {

	      List<Order> orders = orderRepository.findAll();
	      model.addAttribute("orders", orders);

	      return "order";
	  }
}
