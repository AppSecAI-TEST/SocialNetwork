package com.controller;


import com.entity.User;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserServise questionServise;

    @RequestMapping("friends")
    public ModelAndView getAllUsers() {
        List<User> userList = questionServise.findAll();
        return new ModelAndView("booksList", "booksList", userList);
    }

    @RequestMapping(value = "editBook")
    public ModelAndView editUser(@ModelAttribute(value = "book") User user) {
        user = questionServise.getCurrentUser();
        return new ModelAndView("booksForm", "bookObject", user);
    }


    @RequestMapping("/")
    public ModelAndView main(@ModelAttribute(value = "book") User user) {
        user = questionServise.getCurrentUser();
        return new ModelAndView("Main", "bookObject", user);
    }

    @RequestMapping("saveBook")
    public ModelAndView saveUser(@ModelAttribute User user) {
            questionServise.update(user);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping("addToFriends")
    public ModelAndView addToFriends(@RequestParam Long id) {
        questionServise.addToFriends(id);
        return new ModelAndView("redirect:friends");
    }

    @RequestMapping("sendMassage")
    public ModelAndView sendMassage(@RequestParam Long id) {
        questionServise.addToFriends(id);
        return new ModelAndView("redirect:friends");
    }
    @RequestMapping(value = "searchBook",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public User searchUser(@RequestParam("searchName") String searchName) {
        User userList = questionServise.findByUserName(searchName);
        return userList;
    }
}