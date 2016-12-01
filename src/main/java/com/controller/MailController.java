package com.controller;

import com.entity.UserMassageUser;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by bohdan on 17.10.16.
 */
@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private UserServise eventService;
    @RequestMapping("/")
    public ModelAndView mail() {
        List<UserMassageUser> myMap = eventService.getAllDialoge();
        return new ModelAndView("mail", "massageList", myMap);
    }
}
