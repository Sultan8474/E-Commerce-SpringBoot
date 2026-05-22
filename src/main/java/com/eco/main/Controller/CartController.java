package com.eco.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com.eco.main.entity.Cart;
import com.eco.main.entity.Product;
import com.eco.main.reposotory.CartRepository;
import com.eco.main.service.CartService;
import com.eco.main.service.IProductService;


@Controller
public class CartController
{
	@Autowired
	private CartRepository cRepo;
	  @Autowired 
	  private CartService service;
	  @Autowired
	  private IProductService pService;
	  
	  @GetMapping("/test/cart")
	  @ResponseBody
	  public String test() {
	      return "Cart working";
	  }
	  
	  @GetMapping("/cart")
	  public String viewCart(Model model) 
	  {
	      model.addAttribute("cartItems", service.getCartItems());
	      model.addAttribute("total", service.getTotal());
	      return "cart";
	  }
	  
	  @GetMapping("/addToCart") 
	  public String addToCart(@RequestParam Long id,Model model) 
	  {
	      // ✅ Get product from DB
	      Product product = pService.getById(id);

	      // ✅ Check stock
	      if (product.getQuantity() <= 0) {
	    	  return "redirect:/userDashbord?error=outofstock";
	      }
	      // Check if product already in cart
	      Cart cart = cRepo.findByProductName(product.getName());
	      // ✅ Create cart item

	      if (cart != null) {
	          // Product already exists → increase quantity
	          cart.setQuantity(cart.getQuantity() + 1);
	          service.addToCart(cart);
	      } else {
	          // New product → create cart
	          Cart newCart = new Cart();
	          newCart.setProductName(product.getName());
	          newCart.setPrice(product.getPrice());
	          newCart.setQuantity(1);
	          newCart.setImage(product.getImage());

	          service.addToCart(newCart);
	      }
	
	      // 🔥 DECREASE STOCK HERE
	      product.setQuantity(product.getQuantity() - 1);
	      pService.save(product);
	      return "redirect:/userDashbord?success=true";
	  }
	  
	  @GetMapping("/remove/{id}")
	  public String remove(@PathVariable Long id) 
	  {
	  service.deleteItem(id);
	  return "redirect:/cart"; 
	  }

}
