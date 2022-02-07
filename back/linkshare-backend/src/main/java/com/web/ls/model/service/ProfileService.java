package com.web.ls.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.model.dto.User.ProfileEditRequest;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;
	
	public User userInfo(String email){
		return userRepository.findByEmail(email);
    }
	
	public void deleteUser(String email) {
		User user = userRepository.findByEmail(email);
		userRepository.delete(user);
	}
	
	public void profileEdit(ProfileEditRequest request) {
		User user = userRepository.findByEmail(request.getEmail());
		user.setNickname(request.getNickname());
		user.setImagePath(request.getImagePath());
		user.setIntroduce(request.getIntroduce());
		userRepository.save(user);
	}
}
