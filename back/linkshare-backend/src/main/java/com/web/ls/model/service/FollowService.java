package com.web.ls.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.model.dto.Follow.FollowRequest;
import com.web.ls.model.entity.Follow;
import com.web.ls.model.repository.FollowRepository;

@Service
public class FollowService {

	@Autowired
	private FollowRepository followRepository;
	
	public void follow(FollowRequest request){
		Follow follow = followRepository.findByUidAndFolloweeId(request.getUid(), request.getFolloweeId());
		if(follow == null) followRepository.save(request.toEntity());
	}
	
	public void unfollow(FollowRequest request) {
		Follow follow = followRepository.findByUidAndFolloweeId(request.getUid(), request.getFolloweeId());
		if(follow != null) followRepository.delete(follow);
	}
}
