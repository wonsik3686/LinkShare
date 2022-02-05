package com.web.ls.model.repository;

import com.web.ls.model.entity.TreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeInfoRepository extends JpaRepository<TreeInfo, Integer> {
    void deleteByBoxid(int boxid);
}