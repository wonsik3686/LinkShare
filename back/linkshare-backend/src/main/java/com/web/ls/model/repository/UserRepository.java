package com.web.ls.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ls.model.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
	
}
