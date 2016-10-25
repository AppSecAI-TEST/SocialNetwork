package com.controller;

import com.entity.Event;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by bohdan on 14.10.16.
 */
@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private UserServise eventService;

    @RequestMapping("/createEvent")
    public ModelAndView createEvent() {
        return new ModelAndView("eventform");
    }

    @RequestMapping("/addEvent")
    public ModelAndView add(@RequestParam String head, @RequestParam String body) {
        eventService.createEvent(head,body);
        return new ModelAndView("redirect:/event/");
    }
    @RequestMapping("/")
    public ModelAndView getAllUsers() {
        List<Event> eventList = eventService.getEvents();
        return new ModelAndView("events", "eventList", eventList);
    }
}
