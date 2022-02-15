package com.web.ls.model.repository;

import com.web.ls.model.entity.Userbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserboxRepository extends JpaRepository<Userbox, Integer> {

    @Transactional
    void deleteByBoxid(int boxid);
    List<Userbox> findAllByUid(Integer uid);
    Userbox findByBoxidOrderById(Integer boxid);
}