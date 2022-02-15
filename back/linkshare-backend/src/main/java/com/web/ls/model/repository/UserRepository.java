package com.web.ls.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.ls.model.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
	
    User findByEmail(String email);
    User findByNickname(String nickname);
    
    @Query(value = "select * from user u join likes l on u.uid = l.uid where l.boxid = :boxid", nativeQuery = true)
    List<User> findAllByBoxid(@Param("boxid") Integer boxid);
    
    @Query(value = "select * from user u join follow f on u.uid = f.followee_id where f.uid = :uid", nativeQuery = true)
    List<User> findFollowingList(@Param("uid") Integer uid);

    @Query(value = "select * from user u join follow f on u.uid = f.uid where f.followee_id = :uid", nativeQuery = true)
    List<User> findFollowerList(@Param("uid") Integer uid);
}
