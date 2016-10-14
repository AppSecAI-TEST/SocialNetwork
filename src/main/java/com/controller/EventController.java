package com.controller;

import com.service.EventService;
import com.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bohdan on 14.10.16.
 */
@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;
}
