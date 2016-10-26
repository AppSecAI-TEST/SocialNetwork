package com.service;

import com.entity.Event;
import com.entity.User;
import com.entity.UserMassageUser;

import java.sql.Blob;
import java.util.List;

/**
 * Created by bohdan on 15.09.16.
 */
public interface UserServise {
     User create(User question);
     User deleteUser(long id);
     List<User> findAll();
     User update(User question);
     User findByUserName(String username);
     List<User> findByNameOrSurname(String inp);
     User getUser(long id);
     void addUser(String name, String surname, String info, String username, String password, String avatar) throws InterruptedException;
     User getCurrentUser();
     void addToFriends(long user);
     void accept(long id);
     void sandMassage(long id ,String s);
     List<UserMassageUser> getAllMassage();
     void createEvent(String head, String body);
     Event getOneEvent(long id);
     Event deleteEvent(long id);
     Event editEvent(Event event);
     List<Event> getEvents();
     List<Event> findEvent(String param);
     List<Event> getMyEvents();
}
