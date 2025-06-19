package com.engine.server;

import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import com.engine.entity.Entity;
import com.engine.statistics.Idle;

public class Server {
    
    private Entity entity;
    private int id;
    
    private final Queue queue;
    private double lastDeparture;
    private Distribution distribution;
    private double durability;
    private Idle idle;
    private double departureClock;
    
    public Server(ServerCodeGenerator serverCodeGenerator, Distribution distribution, double durability) {
        this.entity = null;
        this.id = serverCodeGenerator.nextCode();
        this.queue = new Queue();
        this.lastDeparture = 0;
        this.distribution = distribution;
        this.durability = durability;
        this.idle = new Idle();
        this.departureClock = 0;
    }
    
    public boolean isBusy(){
        return entity != null;
    }
    
    public void setEntity(Entity entity){
        this.entity = entity;
    }
    
    public Queue getQueue() {
        return queue;
    }
    
    public double getLastDeparture() {
        return lastDeparture;
    }
    
    public void setLastDeparture(double lastDeparture) {
        this.lastDeparture = lastDeparture;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void determineDurability(Randomizer randomizer){
        double randomizerValue = this.distribution.generateValue(randomizer);
        this.durability -= randomizerValue;
    }

    public double getDurability(){
        return this.durability;
    }

    public Idle getIdle(){
        return this.idle;
    }

    public double getDepartureClock() {
        return departureClock;
    }

    public void setDepartureClock(double departureClock) {
        this.departureClock = departureClock;
    }
}
