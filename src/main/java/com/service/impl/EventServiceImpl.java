package com.service.impl;

import com.entity.Event;
import com.repository.EventRepository;
import com.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bohdan on 14.10.16.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Resource
    private EventRepository eventRepository;
    @Override
    public Event createEvent(Event event) {
        Event createEvent = event;
        return eventRepository.saveAndFlush(createEvent);
    }

    @Override
    public Event editEvent(Event event) {
        Event editEvent = eventRepository.findOne(event.getId());
        editEvent.setHead(event.getHead());
        editEvent.setBody(event.getBody());
        return eventRepository.saveAndFlush(editEvent);
    }

    @Override
    public Event deleteEvent(long id) {
        Event deleteEvent = eventRepository.findOne(id);
        eventRepository.delete(deleteEvent);
        return deleteEvent;
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}
