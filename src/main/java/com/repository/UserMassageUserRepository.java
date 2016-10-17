package com.repository;

import com.entity.User;
import com.entity.UserFriend;
import com.entity.UserMassageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bohdan on 10.10.16.
 */
public interface UserMassageUserRepository extends JpaRepository<UserMassageUser,Long> {
    @Query("select uf from UserMassageUser uf where uf.secondUser = :id1 ")
    List<UserMassageUser> massage(@Param("id1") User id);
}
