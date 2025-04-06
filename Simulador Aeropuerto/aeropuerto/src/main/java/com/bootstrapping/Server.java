package com.bootstrapping;

import com.bootstrapping.statistics.Statistics;

public class Server {
    
    private Entity entity;
    private final Queue queue = new Queue();
    private Statistics statistics;
    
    public Server(Queue queue) {
        this.entity = null;
        this.statistics = new Statistics();
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

}
