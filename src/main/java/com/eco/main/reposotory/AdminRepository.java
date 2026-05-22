package com.eco.main.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eco.main.entity.Admin;
import java.util.List;


public interface AdminRepository extends JpaRepository<Admin, Integer> 
{
	Admin findByEmail(String email);

}
