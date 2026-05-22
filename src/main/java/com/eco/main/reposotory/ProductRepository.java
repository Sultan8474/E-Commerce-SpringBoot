package com.eco.main.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;


import com.eco.main.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>
{

}
