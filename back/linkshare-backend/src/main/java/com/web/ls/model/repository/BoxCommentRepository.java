package com.web.ls.model.repository;

import com.web.ls.model.entity.BoxComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxCommentRepository extends JpaRepository<BoxComment, Integer> {
    void deleteByBoxid(int boxid);
    int countByBoxid(Integer boxid);
}