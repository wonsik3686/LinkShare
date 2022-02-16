package com.web.ls.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.ls.model.entity.BoxScrap;
import org.springframework.transaction.annotation.Transactional;

public interface BoxScrapRepository extends JpaRepository<BoxScrap, Integer> {

    @Transactional
    void deleteByBoxid(int boxid);
    int countByBoxid(Integer boxid);
    boolean existsByBoxidAndUid(Integer boxid, Integer uid);
    BoxScrap findByBoxidAndUid(Integer boxid, Integer uid);
    List<BoxScrap> findAllByBoxidOrderByRegtime(Integer boxid);
    List<BoxScrap> findAllByUidOrderByRegtime(Integer uid);
}