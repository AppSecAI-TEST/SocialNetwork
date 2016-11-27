package com.repository;

import com.entity.User;
import com.entity.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bohdan on 08.10.16.
 */
public interface UserFriendRepository extends JpaRepository<UserFriend,Long>{
     @Query("select uf from UserFriend uf where uf.firsUser = :id and uf.accept = 0")
     UserFriend acceptFriend(@Param("id") User id);
     @Query("select ut from UserFriend ut where ut.firsUser = :id or ut.secondUser = :id and ut.accept = 1")
     List<UserFriend> allFriends(@Param("id") User id);
}
