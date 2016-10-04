package com.service.impl;

import com.entity.User;
import com.entity.UserRole;
import com.repository.UserRoleRepository;
import com.repository.UserRepository;
import com.service.UserServise;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserRoleRepository userRoleRepository;
    @Override
    public User create(User user) {
        User createUser = user;
        return userRepository.saveAndFlush(createUser);
    }

    @Override
    public User delete(long id) {
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
        updateQuestion.setAvatar(user.getAvatar());
        return userRepository.saveAndFlush(updateQuestion);
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
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


}
