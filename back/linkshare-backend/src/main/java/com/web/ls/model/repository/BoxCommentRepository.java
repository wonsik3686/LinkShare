package com.web.ls.model.repository;

import com.web.ls.model.entity.BoxComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoxCommentRepository extends JpaRepository<BoxComment, Integer> {

    @Transactional
    void deleteByBoxid(int boxid);
    int countByBoxid(Integer boxid);
    List<BoxComment> findAllByBoxidOrderByRegtimeDesc(Integer boxid);
}