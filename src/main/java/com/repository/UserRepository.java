package com.repository;

import com.entity.User;
import com.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by bohdan on 15.09.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
        @Query("select b from User b where b.username = :username")
        User findByUserName(@Param("username") String username);
}
