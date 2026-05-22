package com.eco.main.service;

import java.util.List;

import com.eco.main.entity.Cart;

public interface ICartService
{
	 public void addToCart(Cart cart);

	    public List<Cart> getCartItems();
	   

	    public void deleteItem(Long id);
	       
	    
	    
	    public double getTotal();
	   

}
