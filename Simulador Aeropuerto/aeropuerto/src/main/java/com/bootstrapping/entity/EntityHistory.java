package com.bootstrapping.entity;

import java.util.ArrayList;
import java.util.List;
import com.bootstrapping.events.Event;

public class EntityHistory {
    private List<Event> entityHistory; //0 arrival 1 departure 
    private double arrivalClock;

    
    public EntityHistory(){
        this.entityHistory = new ArrayList<>(); 
    }
    
    public Event getArrival() {
        return entityHistory.get(0);
    }
    
    public Event getDeparture() {
        return entityHistory.get(1);
    }

    public double getArrivalClock() {
        return arrivalClock;
    }
    
    public void setArrivalClock(double arrivalClock) {
        this.arrivalClock = arrivalClock;
    }

}
