package com.web.ls.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.exception.AlreadyExistEmailException;
import com.web.ls.exception.AlreadyExistNicknameException;
import com.web.ls.model.dto.SignupRequest;
import com.web.ls.model.repository.UserRepository;

@Service
public class SignupService {
	
	@Autowired
	UserRepository userRepository;

	public void signup(SignupRequest request){
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new AlreadyExistEmailException();
		} else if(userRepository.existsByNickname(request.getNickname())) {
			throw new AlreadyExistNicknameException();
		} else {
			userRepository.save(request.toEntity());
		}
	}
}
