package com.web.ls.model.repository;

import com.web.ls.model.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    void deleteByBoxid(int boxid);

    int countByBoxid(Integer boxid);
}