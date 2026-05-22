package com.eco.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCT_DETAILS")
@Data
public class Product 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	@Column
    private String name;
	@Column
    private double price;
	@Column
    private String description;
	@Column
    private int quantity;
	@Column
    private String image; 
}
