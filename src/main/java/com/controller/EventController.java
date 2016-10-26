package com.controller;

import com.entity.Event;
import com.entity.User;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String find) {
        List<Event> eventList = eventService.findEvent(find);
        return new ModelAndView("events", "eventList", eventList);
    }
    @RequestMapping("/myEvents")
    public ModelAndView myEvents() {
        List<Event> eventList = eventService.getMyEvents();
        return new ModelAndView("myEvents", "myEventList", eventList);
    }
    @RequestMapping(value = "/editEvent")
    public ModelAndView editEvent(@RequestParam long id, @ModelAttribute Event event) {
        Long user = eventService.getCurrentUser().getId();
        event = eventService.getOneEvent(id);
        Long user1 = event.getUser().getId();
        if(user == user1){
        return new ModelAndView("editEventForm", "eventObject", event);
        }
        else {
            return new ModelAndView("no");
        }
    }
    @RequestMapping("/saveEvent")
    public ModelAndView saveEvent(@ModelAttribute Event event) {
        eventService.editEvent(event);
        return new ModelAndView("redirect:/event/");
    }

}
