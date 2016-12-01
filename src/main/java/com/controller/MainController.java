package com.controller;

import com.entity.User;
import com.service.UserServise;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    private static final Logger log = Logger.getLogger(MainController.class);
    @Autowired
    UserServise questionServise;

    @RequestMapping("friends")
    public ModelAndView getAllUsers() {
        List<User> userList = questionServise.getAllFriends();
        return new ModelAndView("peoples", "booksList", userList);
    }
    @RequestMapping("allPeople")
    public ModelAndView getAllFuckingPeople() {
        List<User> userList = questionServise.findAll();
        return new ModelAndView("all", "userList", userList);
    }
    @RequestMapping(value = "editBook")
    public ModelAndView editUser(@ModelAttribute(value = "book") User user) {
        user = questionServise.getCurrentUser();
        return new ModelAndView("booksForm", "bookObject", user);
    }
    @RequestMapping("/")
    public ModelAndView main(@ModelAttribute(value = "book") User user) {
        user = questionServise.getCurrentUser();
        log.info("Зайшво користувач:" + user.getUsername() +";");
        return new ModelAndView("Main", "bookObject", user);
    }
    @RequestMapping("/user")
    public ModelAndView getuser(@ModelAttribute(value = "book") User user,@RequestParam Long id) {
        user = questionServise.getUser(id);
        return new ModelAndView("Main", "bookObject", user);
    }
    @RequestMapping("saveBook")
    public ModelAndView saveUser(@ModelAttribute @Valid User user) {
            questionServise.update(user);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping("addToFriends")
    public ModelAndView addToFriends(@RequestParam Long id) {
        questionServise.addToFriends(id);
        return new ModelAndView("redirect:/friends");
    }
    @RequestMapping("friends/accept")
    public ModelAndView accept(@RequestParam Long id) {
        questionServise.accept(id);
        return new ModelAndView("redirect:/friends");
    }
    @RequestMapping("sand")
    public ModelAndView sandMassage(@RequestParam Long id, @RequestParam String massage) {
        questionServise.sandMassage(id,massage);
        return new ModelAndView("redirect:/friends");
    }
    @RequestMapping(value = "searchBook",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public ModelAndView searchUser(@RequestParam("searchName") String searchName) {
        List<User> userList = questionServise.findByNameOrSurname(searchName);
        return new ModelAndView("peoples","booksList",userList);
    }
}