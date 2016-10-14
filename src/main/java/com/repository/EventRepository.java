package com.repository;

import com.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bohdan on 14.10.16.
 */
public interface EventRepository extends JpaRepository<Event,Long> {
}
