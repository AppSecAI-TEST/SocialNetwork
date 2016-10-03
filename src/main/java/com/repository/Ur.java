package com.repository;

import com.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bohdan on 26.09.16.
 */
public interface Ur extends JpaRepository<UserRole,Long> {
}
