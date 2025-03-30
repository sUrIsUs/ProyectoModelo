package com.classes;

public abstract class Event {
    int type; // 0 -> Departure, 1 -> End, 2 -> Arrival
    double clock;
    Entity entity;

    public Event(int type, double clock, Entity entity){
        this.type = type;
        this.clock = clock;
        this.entity = entity;
    }

    public abstract void planificate(FEL fel, Event event, Distribution distribution, Randomizer randomizer);

    public int getType() {
        return type;
    }

    public double getClock() {
        return clock;
    }

    public Entity getEntity() {
        return entity;
    }
    
}
