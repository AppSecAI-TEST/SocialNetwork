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
        return new ModelAndView("peoples", "usersList", userList);
    }
    @RequestMapping("allPeople")
    public ModelAndView getAllPeople() {
        List<User> userList = questionServise.findAll();
        return new ModelAndView("all", "userList", userList);
    }
    @RequestMapping(value = "editUser")
    public ModelAndView editUser(@ModelAttribute(value = "user") User user) {
        user = questionServise.getCurrentUser();
        return new ModelAndView("usersForm", "userObject", user);
    }
    @RequestMapping("/")
    public ModelAndView main(@ModelAttribute(value = "user") User user) {
        try {
            user = questionServise.getCurrentUser();
        }
        catch (Exception e)
        {
            return new ModelAndView("redirect:/login/");
        }

        return new ModelAndView("Main", "userObject", user);
    }
    @RequestMapping("/user")
    public ModelAndView getuser(@ModelAttribute(value = "user") User user,@RequestParam Long id) {
        user = questionServise.getUser(id);
        return new ModelAndView("Main", "userObject", user);
    }
    @RequestMapping("saveUser")
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
    @RequestMapping("send")
    public ModelAndView sandMassage(@RequestParam Long id, @RequestParam String massage) {
        questionServise.sandMassage(id,massage);
        return new ModelAndView("redirect:/friends");
    }
    @RequestMapping(value = "searchUser",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public ModelAndView searchUser(@RequestParam("searchName") String searchName) {
        List<User> userList = questionServise.findByNameOrSurname(searchName);
        return new ModelAndView("peoples","booksUser",userList);
    }
}