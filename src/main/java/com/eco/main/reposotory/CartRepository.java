package com.eco.main.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eco.main.entity.Cart;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long> 
{
	Cart findByProductName(String productName);

}
