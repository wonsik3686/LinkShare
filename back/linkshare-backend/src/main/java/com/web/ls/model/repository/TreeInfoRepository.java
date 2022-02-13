package com.web.ls.model.repository;

import com.web.ls.model.entity.TreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TreeInfoRepository extends JpaRepository<TreeInfo, Integer> {
    @Transactional
    void deleteByBoxid(int boxid);

    @Transactional
    void deleteByBoxid(Integer boxid);

    List<TreeInfo> findAllByBoxid(Integer boxid);
    boolean existsByBoxid(Integer boxid);
}