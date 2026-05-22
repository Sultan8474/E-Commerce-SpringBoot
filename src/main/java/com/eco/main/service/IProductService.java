package com.eco.main.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.eco.main.entity.Product;

public interface IProductService 
{
	 public List<Product> getAllProducts();
	    

	    public void save(Product product);
	   

	    public void delete(Long id);
	   
	    
	    public Page<Product> getProducts(int page, int size);
	    
	    Product getById(Long id);
	  
}
