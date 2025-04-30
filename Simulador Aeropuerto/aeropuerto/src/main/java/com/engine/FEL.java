package com.engine;

import java.util.ArrayList;
import java.util.List;

import com.engine.comparators.EventPrioritizer;
import com.engine.events.Event;

public class FEL {
    private List<Event> fel;
    private EventPrioritizer eventPrioritizer;

    public FEL(EventPrioritizer eventPrioritizer){
        fel = new ArrayList<>();
        this.eventPrioritizer = eventPrioritizer;
    }

    public void addEvent(Event event){
        this.fel.add(event);
        this.fel.sort(this.eventPrioritizer);
    }

    public Event inminent(){
        return fel.remove(0);
    }

    @Override
    public String toString() {
        String output = "================ FEL ================\n";

        for (Event event : this.fel) {
            output += "[" + event.getClock() + "," + event.getType() + "]\n";
        }

        return output+= "*****************************";
    }
} 