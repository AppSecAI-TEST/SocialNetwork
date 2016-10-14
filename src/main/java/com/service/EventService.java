package com.service;

import com.entity.Event;

import java.util.List;

/**
 * Created by bohdan on 14.10.16.
 */
public interface EventService {
    Event createEvent(Event event);
    Event editEvent(Event event);
    Event deleteEvent(long id);
    List<Event> getEvents();

}
