package com.web.ls.model.service;

import com.web.ls.model.dto.linkbox.LinkboxCreateRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentRequest;
import com.web.ls.model.dto.linkbox.comment.BoxCommentUpdateRequest;
import com.web.ls.model.entity.BoxComment;
import com.web.ls.model.repository.BoxCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxCommentService {

    @Autowired
    BoxCommentRepository boxCommentRepository;

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

    public List<BoxComment> searchBoxCommentByBoxid(Integer boxid) {
        return boxCommentRepository.findAllByBoxidOrderByRegtimeDesc(boxid);
    }
}
