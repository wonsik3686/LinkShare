package com.web.ls.model.repository;

import com.web.ls.model.entity.TreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeInfoRepository extends JpaRepository<TreeInfo, Integer> {
    void deleteByBoxid(int boxid);
    List<TreeInfo> findAllByBoxid(Integer boxid);
    boolean existsByBoxid(Integer boxid);
}