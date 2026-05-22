package com.eco.main.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eco.main.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{

}
