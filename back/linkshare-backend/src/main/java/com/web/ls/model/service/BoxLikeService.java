package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.like.LikesCreateRequest;
import com.web.ls.model.entity.Likes;
import com.web.ls.model.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxLikeService {

    @Autowired
    LikesRepository likesRepository;

    public void createLinkboxLike(LikesCreateRequest request) {
        Likes like = request.toEntity();
        likesRepository.save(like);
    }

    public void deleteLinkboxLike(Integer likeid) {
        likesRepository.deleteById(likeid);
    }
}
