package com.web.ls.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.model.dto.Follow.FollowRequest;
import com.web.ls.model.dto.User.UserResponse;
import com.web.ls.model.entity.Follow;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.FollowRepository;
import com.web.ls.model.repository.UserRepository;

@Service
public class FollowService {

	@Autowired
	private FollowRepository followRepository;

	@Autowired
	private UserRepository userRepository;
	
	public void follow(FollowRequest request){
		Follow follow = followRepository.findByUidAndFolloweeId(request.getUid(), request.getFolloweeId());
		if(follow == null) followRepository.save(request.toEntity());
	}
	
	public void unfollow(FollowRequest request) {
		Follow follow = followRepository.findByUidAndFolloweeId(request.getUid(), request.getFolloweeId());
		if(follow != null) followRepository.delete(follow);
	}
	
	public int following(int id) {
		return followRepository.countByUid(id);
	}
	
	public int follower(int id) {
		return followRepository.countByFolloweeId(id);
	}
	
	public List<UserResponse> searchFollowingListByUserId(Integer uid){
		List<UserResponse> list = new ArrayList<>();
		userRepository.findFollowingList(uid).forEach(i -> {
			list.add(i.toResponse());
		});
		return list;
	}
	
	public List<UserResponse> searchFollowerListByUserId(Integer uid){
		List<UserResponse> list = new ArrayList<>();
		userRepository.findFollowerList(uid).forEach(i -> {
			list.add(i.toResponse());
		});
		return list;
	}
}
