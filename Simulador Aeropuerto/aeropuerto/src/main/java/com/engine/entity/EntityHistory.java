package com.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.engine.events.Event;

public class EntityHistory {
    private List<Event> entityHistory; //0 arrival 1 departure 
    private double serviceArrivalClock;

    
    public EntityHistory(){
        this.entityHistory = new ArrayList<>(); 
    }
    
    public Event getArrival() {
        return entityHistory.getFirst();
    }
    
    public Event getDeparture() {
        return entityHistory.getLast();
    }

    public double getServiceArrivalClock() {
        return serviceArrivalClock;
    }
    
    public void setServiceArrivalClock(double arrivalClock) {
        this.serviceArrivalClock = arrivalClock;
    }

    public void addArrival(Event arrival){
        this.entityHistory.add(arrival);
    }

    public void addDeparture(Event departure){
        this.entityHistory.addLast(departure);
    }

}
