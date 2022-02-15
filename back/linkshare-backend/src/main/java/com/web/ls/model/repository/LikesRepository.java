package com.web.ls.model.repository;

import com.web.ls.model.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    void deleteByBoxid(int boxid);

    int countByBoxid(Integer boxid);
    
    boolean existsByBoxidAndUid(Integer boxid, Integer uid);

    Likes findByBoxidAndUid(Integer boxid, Integer uid);

    @Query(value = "select l.boxid from likes l group by l.boxid order by count(*) desc limit 6", nativeQuery = true)
    List<Integer> findTopBoxIdOrderByCountLimit6();
}