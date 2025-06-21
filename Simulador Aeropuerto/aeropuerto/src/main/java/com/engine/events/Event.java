package com.engine.events;
import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.distribution.DistributionList;
import com.engine.entity.Entity;
import com.engine.entity.EntityFactory;
import com.engine.server.Servers;
import com.engine.statistics.Statistics;

public abstract class Event {
    int type; // 0 -> Departure, 1 -> End, 2 -> Arrival
    double clock;
    Entity entity;
    DistributionList distributionList;

    public Event(int type, double clock, Entity entity, DistributionList distributionList){
        this.type = type;
        this.clock = clock;
        this.entity = entity;
        this.distributionList = distributionList;
    }

    public abstract void planificate(FEL fel, Servers servers, Event event, Randomizer randomizer, EntityFactory entityFactory, Statistics statistics);

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
