package com.bootstrapping.comparators;

import java.util.Comparator;

import com.bootstrapping.events.Event;

public class EventPrioritizer implements Comparator<Event>{

    public EventPrioritizer(){}

    @Override
    public int compare(Event e1, Event e2) {
        if(e1.getClock() < e2.getClock()){
            return -1;
        }    
        else if(e1.getClock() > e2.getClock()){
            return 1;
        }
        else if(e1.getType() < e2.getType()){
            return -1;
        }
        else{
            return 1;
        }
    }
    
}
