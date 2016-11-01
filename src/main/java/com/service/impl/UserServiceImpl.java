package com.service.impl;

import com.config.AppConfig;
import com.entity.*;
import com.repository.*;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bohdan on 15.09.16.
 */
@Service
@Transactional
public class UserServiceImpl implements UserServise {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserFriendRepository userFriendRepository;
    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private UserMassageUserRepository userMassageUserRepository;
    @Resource
    private EventRepository eventRepository;
    @Autowired
    private AppConfig appConfig;
    @Override
    public User create(User user) {
        User createUser = user;
        return userRepository.saveAndFlush(createUser);
    }

    @Override
    public User deleteUser(long id) {
        User deletedUser = userRepository.findOne(id);
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        User updateQuestion = userRepository.findOne(user.getId());
        updateQuestion.setName(user.getName());
        updateQuestion.setSurname(user.getSurname());
        updateQuestion.setInfo(user.getInfo());
        updateQuestion.setUsername(user.getUsername());
        updateQuestion.setPassword(user.getPassword());
        return userRepository.saveAndFlush(updateQuestion);
    }
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<User> findByNameOrSurname(String inp) {
        return  userRepository.findUsers(inp);
    }

    @Override
    public User getUser(long id) {
        return  userRepository.getUser(id);
    }
    @Override
    public void addUser(String name, String surname, String info, String username, String password, String avatar) throws InterruptedException {
        User user = new User();
        UserRole userRole = new UserRole();
        user.setName(name);
        user.setSurname(surname);
        user.setInfo(info);
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        user.setEnabled(true);
        user.setAvatar(avatar);
        userRole.setUser(user);
        userRole.setRole("ROLE_USER");
        userRoleRepository.save(userRole);
        Thread.sleep(200);
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        User user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String name =userDetail.getUsername();
        return user = findByUserName(name);
    }
    @Override
    public void addToFriends(long id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = appConfig.transactionManager().getTransaction(def);
        try {
            UserFriend userFriend = new UserFriend();
            User user = getUser(id);
            User user1 = getCurrentUser();
            userFriend.setFirsUser(user1);
            userFriend.setSecondUser(user);
            userFriend.setAccept(0);
            userFriendRepository.save(userFriend);
        }
        catch (Exception ex) {
            appConfig.transactionManager().rollback(status);
            throw ex;
        }
        appConfig.transactionManager().commit(status);
    }
    @Override
    public void accept(long id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = appConfig.transactionManager().getTransaction(def);
        try {
         User user = userRepository.getUser(id);
        UserFriend userFriend = userFriendRepository.acceptFriend(user);
        userFriend.setAccept(1);
        userFriendRepository.saveAndFlush(userFriend);
        }
        catch (Exception ex) {
            appConfig.transactionManager().rollback(status);
            throw ex;
        }
        appConfig.transactionManager().commit(status);
    }
    @Override
    public void sandMassage(long id , String s) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = appConfig.transactionManager().getTransaction(def);
        try {
            UserMassageUser userFriend = new UserMassageUser();
            User user = getUser(id);
            User user1 = getCurrentUser();
            userFriend.setFirsUser(user1);
            userFriend.setSecondUser(user);
            userFriend.setMassage(s);
            userMassageUserRepository.save(userFriend);
        }
        catch (Exception ex) {
            appConfig.transactionManager().rollback(status);
            throw ex;
        }
        appConfig.transactionManager().commit(status);
    }

    @Override
    public List<UserMassageUser> getAllMassage() {
        User user =getCurrentUser();
        List<UserMassageUser> userMassageUsers = userMassageUserRepository.massage(user);
        return userMassageUsers;
    }

    @Override
    public void createEvent(String head,String body) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = appConfig.transactionManager().getTransaction(def);
        try {
            Event createEvent = new Event();
            createEvent.setHead(head);
            createEvent.setBody(body);
            createEvent.setUser(getCurrentUser());
            eventRepository.save(createEvent);
        }
        catch (Exception ex) {
            appConfig.transactionManager().rollback(status);
            throw ex;
        }
        appConfig.transactionManager().commit(status);
    }

    @Override
    public Event getOneEvent(long id) {
        Event event = eventRepository.findOne(id);
        return event;
    }

    @Override
    public Event deleteEvent(long id) {
        Event deleteEvent = eventRepository.findOne(id);
        eventRepository.delete(deleteEvent);
        return deleteEvent;
    }
    @Override
    public Event editEvent(Event event) {
        Event editEvent = eventRepository.findOne(event.getId());
        editEvent.setHead(event.getHead());
        editEvent.setBody(event.getBody());
        return eventRepository.save(editEvent);
    }
    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findEvent(String param) {
        return eventRepository.findEvents(param);
    }
    @Override
    public List<Event> getMyEvents() {
        User user = getCurrentUser();
        return eventRepository.getPersonEvents(user);
    }

}
