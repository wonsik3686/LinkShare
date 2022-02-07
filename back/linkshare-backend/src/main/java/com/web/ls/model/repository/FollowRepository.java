package com.web.ls.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ls.model.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer>{

	Follow findByUidAndFolloweeId(int uid, int followeeId);
}
