package com.repository;

import com.entity.User;
import com.entity.UserFriend;
import com.entity.UserMassageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by bohdan on 10.10.16.
 */
public interface UserMassageUserRepository extends JpaRepository<UserMassageUser,Long> {
    @Query("select uf from UserMassageUser uf where uf.firsUser = :id1 and uf.secondUser =:id2")
    UserMassageUser massage(@Param("id1") User id , @Param("id2") User sec);
}
