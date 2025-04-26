package com.bootstrapping.server;

import com.bootstrapping.entity.Entity;
import com.bootstrapping.statistics.Statistics;

public class Server {
    
    private Entity entity;
    private int id;
    
    private final Queue queue;
    private Statistics statistics;
    private double lastDeparture;
    
    
    public Server(ServerCodeGenerator serverCodeGenerator, Statistics statistics) {
        this.entity = null;
        this.id = serverCodeGenerator.nextCode();
        this.queue = new Queue();
        this.statistics = statistics;
        this.lastDeparture = 0;
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
}
