package dev.dashaun.ui.calendar.client;

import dev.dashaun.ui.calendar.views.Event;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface CalendarClient {
    @PostExchange("/api/events/")
    Event createEvent(@RequestBody Event event);
    
    @GetExchange("/api/events/")
    List<Event> getEvents();
    
    @GetExchange("/api/events/{id}")
    Event getEventById(@PathVariable String id);
    
    @DeleteExchange("/api/events/{id}")
    void deleteEventById(@PathVariable String id);
    
    @GetExchange("/actuator/health")
    String serviceHealth();
}
