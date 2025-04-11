package com.bootstrapping.fel;

import java.util.ArrayList;
import java.util.List;

import com.bootstrapping.comparators.EventPrioritizer;
import com.bootstrapping.events.Event;

public class FEL {
    private List<Event> fel;
    private EventPrioritizer eventPrioritizer;

    public FEL(EventPrioritizer eventPrioritizer){
        fel = new ArrayList<>();
        this.eventPrioritizer = eventPrioritizer;
    }

    public void addEvent(Event event){
        fel.add(event);
        fel.sort(eventPrioritizer);
    }

    public Event inminent(){
        return fel.removeFirst();
    }
} 