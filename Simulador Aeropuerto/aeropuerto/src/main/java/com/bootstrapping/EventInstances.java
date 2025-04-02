package com.bootstrapping;

public abstract class EventInstances {
    
    private int eventInstances;

    public EventInstances() {
        this.eventInstances = 0;
    }

    public int getEventInstances() {
        return eventInstances;
    }

    public void process (){
        this.eventInstances ++;
    }

}
