package com.bootstrapping.server;

import com.bootstrapping.Entity;
import com.bootstrapping.statistics.Statistics;

public class Server {
    
    private Entity entity;
    private final Queue queue = new Queue();
    private Statistics statistics;
    private double lastDeparture;
    
    
    public Server(Queue queue) {
        this.entity = null;
        this.statistics = new Statistics();
        this.lastDeparture = 0;
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

}
