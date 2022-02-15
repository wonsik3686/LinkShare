package com.web.ls.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ls.model.dto.linkbox.like.LikesCreateRequest;
import com.web.ls.model.entity.Likes;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.LikesRepository;
import com.web.ls.model.repository.UserRepository;

@Service
public class BoxLikeService {

    @Autowired
    LikesRepository likesRepository;
    
    @Autowired
    UserRepository userRepository;

    public void createLinkboxLike(LikesCreateRequest request) {
        Likes like = request.toEntity();
        if(!likesRepository.existsByBoxidAndUid(request.getBoxid(), request.getUid())) {
        	likesRepository.save(like);
        }
    }

    public void deleteLinkboxLike(LikesCreateRequest request) {
    	Likes like = likesRepository.findByBoxidAndUid(request.getBoxid(),request.getUid());
    	if(like != null) {
    		likesRepository.delete(like);
    	}
    }

    public List<User> searchLikeByBoxid(Integer boxid) {
    	return userRepository.findAllByBoxid(boxid);
    }
    
    public boolean checkLikeUser(Integer boxid, Integer uid) {
    	return likesRepository.existsByBoxidAndUid(boxid, uid);
    }
}
