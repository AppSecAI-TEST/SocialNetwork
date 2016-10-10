package com.service;

import com.entity.User;

import java.sql.Blob;
import java.util.List;

/**
 * Created by bohdan on 15.09.16.
 */
public interface UserServise {
     User create(User question);
     User delete(long id) ;
     List<User> findAll();
     User update(User question);
     User findByUserName(String username);
     User getUser(long id);
     void addUser(String name, String surname, String info, String username, String password, String avatar) throws InterruptedException;
     User getCurrentUser();
     void addToFriends(long user);
     void accept(long id);
}
