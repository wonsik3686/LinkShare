package com.web.ls.model.repository;

import com.web.ls.model.entity.LinkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LinkDetailRepository extends JpaRepository<LinkDetail, Integer> {

    @Transactional
    void deleteByBoxid(int boxid);

    List<LinkDetail> findAllByBoxid(Integer boxid);


}