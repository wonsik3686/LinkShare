package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentResponse;
import com.web.ls.model.dto.linkbox.comment.BoxCommentUpdateRequest;
import com.web.ls.model.entity.BoxComment;
import com.web.ls.model.entity.User;
import com.web.ls.model.repository.BoxCommentRepository;
import com.web.ls.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoxCommentService {

    @Autowired
    BoxCommentRepository boxCommentRepository;

    @Autowired
    UserRepository userRepository;

    public void createBoxComment(BoxCommentRequest request) {
        BoxComment comment = request.toEntity();
        boxCommentRepository.save(comment);
    }

    public void deleteBoxComment(Integer commentId) {
        boxCommentRepository.deleteById(commentId);
    }

    public void updateBoxComment(BoxCommentUpdateRequest request) {
        BoxComment comment = boxCommentRepository.getById(request.getId());
        comment.setContent(request.getContent());
        boxCommentRepository.save(comment);
    }

    public List<BoxCommentResponse> searchBoxCommentByBoxid(Integer boxid) {

        List<BoxCommentResponse> list = new ArrayList<BoxCommentResponse>();

        for ( BoxComment comment : boxCommentRepository.findAllByBoxidOrderByRegtimeDesc(boxid)) {

            BoxCommentResponse boxCommentResponse = BoxCommentResponse.fromEntity(comment);
            Optional<User> user = userRepository.findById(comment.getUid());
            if(user.isPresent()) {
                boxCommentResponse.setNickName(user.get().getNickname());
            }
            list.add(boxCommentResponse);
        }

        return list;
    }
}
