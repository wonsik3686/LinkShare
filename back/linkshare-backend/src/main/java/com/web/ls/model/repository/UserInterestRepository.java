package com.web.ls.model.repository;

import com.web.ls.model.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {

    List<UserInterest> findAllByUid(Integer uid);
}