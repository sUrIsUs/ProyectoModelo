package com.engine.server;

import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import com.engine.entity.Entity;
import com.engine.statistics.Statistics;

public class Server {
    
    private Entity entity;
    private int id;
    
    private final Queue queue;
    private Statistics statistics;
    private double lastDeparture;
    private Distribution distribution;
    private double durability;
    
    public Server(ServerCodeGenerator serverCodeGenerator, Statistics statistics, Distribution distribution, double durability) {
        this.entity = null;
        this.id = serverCodeGenerator.nextCode();
        this.queue = new Queue();
        this.statistics = statistics;
        this.lastDeparture = 0;
        this.distribution = distribution;
        this.durability = durability;
    }

    public void setStatistics(Statistics statistics){
        this.statistics = statistics;
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
    
    public Statistics getStatistics(){
        return this.statistics;
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
        System.out.println("Randomizer value: " + randomizerValue);
        this.durability -= randomizerValue;
    }

    public double getDurability(){
        return this.durability;
    }
}
