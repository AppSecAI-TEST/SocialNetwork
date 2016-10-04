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

/**
 * Created by bohdan on 15.09.16.
 */
@Controller
public class MainController {
    @Autowired
    UserServise questionServise;
    @RequestMapping("/freinds")
    public ModelAndView getAllBooks() {
        List<User> bookList = questionServise.findAll();
        return new ModelAndView("booksList", "booksList", bookList);
    }

    @RequestMapping(value = "editBook")
    public ModelAndView editBooks(@ModelAttribute(value = "book") User book) {
        book = questionServise.getCurrentUser();
        return new ModelAndView("booksForm", "bookObject", book);
    }


    @RequestMapping("/")
    public ModelAndView main(@ModelAttribute(value = "book") User book) {
        book = questionServise.getCurrentUser();
        return new ModelAndView("Main", "bookObject", book);
    }

    @RequestMapping("saveBook")
    public ModelAndView saveBooks(@ModelAttribute User book) {
            questionServise.update(book);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping(value = "searchBook",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public User searchBook(@RequestParam("searchName") String searchName) {
        User booksList = questionServise.findByUserName(searchName);
        return booksList;
    }
}