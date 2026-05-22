package com.eco.main.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eco.main.entity.Cart;
import com.eco.main.reposotory.CartRepository;

@Service
public class CartService implements ICartService
{
	@Autowired
    private CartRepository repo;

	@Override
    public void addToCart(Cart cart)
    {
        repo.save(cart);
    }
	
     @Override
    public List<Cart> getCartItems() 
     {
        return repo.findAll();
    }
     
   @Override
    public void deleteItem(Long id)
   {
        repo.deleteById(id);
    }
   
    @Override
    public double getTotal() 
    {
        return repo.findAll()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

}
