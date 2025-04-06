package com.bootstrapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.bootstrapping.comparators.EventPrioritizer;
import com.bootstrapping.events.Event;

public class FEL {
    private List<Event> fel;
    private EventPrioritizer eventSort;

    public FEL(){
        fel = new ArrayList<>();
    }

    public void addEvent(Event event){
        fel.add(event);
    }

    public Event inminent(){
        return fel.removeFirst();
    }
} 