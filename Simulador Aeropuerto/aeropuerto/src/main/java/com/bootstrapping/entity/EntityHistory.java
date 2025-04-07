package com.bootstrapping.entity;

import java.util.ArrayList;
import java.util.List;

import com.bootstrapping.events.Event;

public class EntityHistory {
    private List<Event> entityHistory;
    private double arrivalClock;

    
    public EntityHistory(){
        this.entityHistory = new ArrayList<>(); 
    }
    
    public Event getDeparture() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeparture'");
    }
    
    public Event getArrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrival'");
    }
    public double getArrivalClock() {
        return arrivalClock;
    }
    
    public void setArrivalClock(double arrivalClock) {
        this.arrivalClock = arrivalClock;
    }

}
