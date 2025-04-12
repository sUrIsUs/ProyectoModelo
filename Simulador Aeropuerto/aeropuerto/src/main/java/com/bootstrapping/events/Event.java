package com.bootstrapping.events;
import com.bootstrapping.Randomizer;
import com.bootstrapping.entity.Entity;
import com.bootstrapping.FEL;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;

public abstract class Event {
    int type; // 0 -> Departure, 1 -> End, 2 -> Arrival
    double clock;
    Entity entity;

    public Event(int type, double clock, Entity entity){
        this.type = type;
        this.clock = clock;
        this.entity = entity;
    }

    public abstract Server planificate(FEL fel, Servers servers, Event event, Randomizer randomizer);

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
