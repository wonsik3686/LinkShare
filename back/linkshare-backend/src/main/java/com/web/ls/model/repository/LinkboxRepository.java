package com.web.ls.model.repository;

import com.web.ls.model.entity.Linkbox;
import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkboxRepository extends JpaRepository<Linkbox, Integer> {

    @Query("update Linkbox l set l.viewCount = l.viewCount + 1 where l.id = :inputBoxid")
    void setViewCountPlusOne(@Param("inputBoxid") int boxid);

    @Query("update Linkbox l set l.title = :inputTitle where l.id = :inputBoxid")
    void setTitle(@Param("inputTitle") String title, @Param("inputBoxid") int boxid);

    @Query("update Linkbox l set l.title = :inputDesc where l.id = :inputBoxid")
    void setDesc(@Param("inputDesc") String desc, @Param("inputBoxid") int boxid);

    @Query(value = "select l.boxid from likes l group by l.boxid order by count(*) desc limit 6", nativeQuery = true)
    List<Integer> findTopBoxIdOrderByCountLimit6();
}