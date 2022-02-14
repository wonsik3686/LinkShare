package com.web.ls.model.service;

import com.web.ls.exception.NotFoundUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.model.dto.User.ProfileEditRequest;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;
	
	public User userInfo(String email){
		return userRepository.findByEmail(email);
    }
	
	public User userInfo(int id){
		return userRepository.findById(id).get();
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

	public void updateUserImgPath(Integer uid, String path) {
		User user = userRepository.getById(uid);
		user.setImagePath(path);
		userRepository.save(user);
	}

	public String searchUserImg(Integer uid, String email, String nickname) {
		if(uid != null && uid != 0) {
			return searchUserImgPathById(uid);
		} else if(!email.equals("")) {
			return searchUserImgPathByEmail(email);
		} else if(!email.equals("")) {
			return searchUserImgPathByNickname(nickname);
		}
		return  "";
	}

	public String searchUserImgPathById(Integer uid) {
		Optional<User> user = userRepository.findById(uid);
		if(user.isPresent()) {
			return user.get().getImagePath();
		} else {
			throw new NotFoundUserException();
		}
	}

	public String searchUserImgPathByEmail(String email) {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
		if(user.isPresent()) {
			return user.get().getImagePath();
		} else {
			throw new NotFoundUserException();
		}
	}

	public String searchUserImgPathByNickname(String nickname) {
		Optional<User> user = Optional.ofNullable(userRepository.findByNickname(nickname));
		if(user.isPresent()) {
			return user.get().getImagePath();
		} else {
			throw new NotFoundUserException();
		}
	}

}
