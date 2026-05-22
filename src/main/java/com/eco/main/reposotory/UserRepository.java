package com.eco.main.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eco.main.entity.User;



public interface UserRepository extends JpaRepository<User, Integer>
{
	User findByEmail(String email);

}
