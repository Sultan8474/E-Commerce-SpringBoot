package com.eco.main.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eco.main.entity.Product;
import com.eco.main.reposotory.ProductRepository;

@Service
public class ProductService implements IProductService
{
	 @Autowired
	    private ProductRepository repo;

	 @Override
	    public List<Product> getAllProducts() 
	    {
	        return repo.findAll();
	    }

	 @Override
	    public void save(Product product) 
	 {
	        repo.save(product);
	    }
	 
	 @Override
	    public void delete(Long id) 
	 {
	        repo.deleteById(id);
	    }
	 
	 @Override
	    public Page<Product> getProducts(int page, int size) 
	 {
	        return repo.findAll(PageRequest.of(page, size));
	    }

	 @Override
	 public Product getById(Long id)
	 {
		 return repo.findById(id).orElse(null);
	 }

}
