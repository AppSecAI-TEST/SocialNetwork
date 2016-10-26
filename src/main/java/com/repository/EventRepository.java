package com.repository;

import com.entity.Event;
import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bohdan on 14.10.16.
 */
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("select sd from Event sd where sd.head LIKE CONCAT('%',:param,'%')")
    List<Event> findEvents(@Param("param") String param);
    @Query("select de from Event de where  de.user =:user")
    List<Event> getPersonEvents(@Param("user") User user);
}
