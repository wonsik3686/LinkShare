package com.web.ls.model.repository;

import com.web.ls.model.entity.BoxInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoxInterestRepository extends JpaRepository<BoxInterest, Integer> {

    void deleteByBoxid(int boxid);

    @Query("select a.name from Interest a where a.id = any (select b.interestId from BoxInterest b where :boxid)")
    List<String> findInterestNameByBoxid(@Param("boxid") int boxid);
}