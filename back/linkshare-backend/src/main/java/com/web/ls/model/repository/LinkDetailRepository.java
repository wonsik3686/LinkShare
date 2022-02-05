package com.web.ls.model.repository;

import com.web.ls.model.entity.LinkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkDetailRepository extends JpaRepository<LinkDetail, Integer> {
    void deleteByBoxid(int boxid);
}