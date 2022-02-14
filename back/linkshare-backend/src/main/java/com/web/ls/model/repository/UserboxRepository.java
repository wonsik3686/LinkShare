package com.web.ls.model.repository;

import com.web.ls.model.entity.Userbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserboxRepository extends JpaRepository<Userbox, Integer> {
    void deleteByBoxid(int boxid);
    List<Userbox> findAllByUid(Integer uid);
    Userbox findByBoxidOrderById(Integer boxid);
}