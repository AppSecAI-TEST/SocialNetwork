package com.controller;

import com.entity.User;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


/**
 * Created by bohdan on 26.09.16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServise userServise;

    @RequestMapping("createUser")
    public ModelAndView createUser(@ModelAttribute User book) {
        return new ModelAndView("userForm");
    }

    @RequestMapping("addUser")
    public ModelAndView add(@RequestParam String name, @RequestParam String surname, @RequestParam String info, @RequestParam String username, @RequestParam String password,@RequestParam String avatar) throws InterruptedException {
        userServise.addUser(name,surname,info,username, password,avatar);
        return new ModelAndView("login");
    }
}
