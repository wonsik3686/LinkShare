package com.web.ls.model.repository;

import com.web.ls.model.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

    boolean existsByName(String name);

    boolean findByName(String name);

    @Query(value = "SELECT interest_id FROM interest i WHERE i.name = :interestName", nativeQuery = true)
    int findIdByName(String interestName);
}