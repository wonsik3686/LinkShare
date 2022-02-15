package com.web.ls.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.exception.NotFoundUserException;
import com.web.ls.model.dto.User.ProfileEditRequest;
import com.web.ls.model.dto.User.UserInterestRequest;
import com.web.ls.model.entity.Interest;
import com.web.ls.model.entity.User;
import com.web.ls.model.entity.UserInterest;
import com.web.ls.model.repository.InterestRepository;
import com.web.ls.model.repository.UserInterestRepository;
import com.web.ls.model.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InterestRepository interestRepository;

	@Autowired
	private UserInterestRepository userInterestRepository;
	
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

	public void createUserInterest(UserInterestRequest request) {
		request.getInterests().forEach(i ->{
			if(!interestRepository.existsByName(i)) {
				 Interest interest = new Interest();
		         interest.setName(i);
		         interestRepository.save(interest);
			}
			
			UserInterest userInterest = new UserInterest();
	        userInterest.setUid(request.getUid());
	        userInterest.setInterestId(interestRepository.findIdByName(i));
	        if(!userInterestRepository.existsByUidAndInterestId(userInterest.getUid(), userInterest.getInterestId())){
	        	userInterestRepository.save(userInterest);
	        }
		});
	}
	
    public void deleteUserInterest(int uid, String interest) {
    	UserInterest userInterest = userInterestRepository.findByUidAndInterestId(uid,interestRepository.findIdByName(interest));
    	userInterestRepository.delete(userInterest);
    }
    
    public List<String> searchInterestsByUid(int uid){
    	List<String> list = new ArrayList<>();
    	userInterestRepository.findAllByUid(uid).forEach(i ->{
    		list.add(interestRepository.findById(i.getInterestId()).get().getName());
    	});
    	return list;
    }
}
