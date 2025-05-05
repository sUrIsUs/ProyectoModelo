package com.engine.comparators;

import java.util.Comparator;

import com.engine.events.Event;

public class EventPrioritizer implements Comparator<Event>{

    public EventPrioritizer(){}

    @Override
    public int compare(Event e1, Event e2) {

        return Comparator
            .comparing(Event::getClock)
            .thenComparing(Event::getType)
            .compare(e1, e2);
            
    }
    
}
