package com.example.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.bookstore.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	//Optional<User> findByUserName(String userName);
	User findByUserName(String userName);
}
