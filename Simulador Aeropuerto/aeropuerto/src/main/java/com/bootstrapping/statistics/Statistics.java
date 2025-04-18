package com.bootstrapping.statistics;

import com.bootstrapping.entity.EntityHistory;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;

public abstract class Statistics {

    // Variables para indicar la cantidad de instancias de los eventos de arribo y salidas
    private static int arrivalInstances = 0;
    private static int departureInstances = 0;
    
    private Idle idle;
    private Transit transit;
    private Wait wait;

    public Statistics() {
        this.idle = new Idle();
        this.transit = new Transit();
        this.wait = new Wait();
    }
    // Variables estáticas
    
    public int getArrivalInstances() {
        return arrivalInstances;
    }
    
    public int getDepartureInstances() {
        return departureInstances;
    }
    
    public Idle getIdle() {
        return idle;
    }
    
    public Transit getTransit() {
        return transit;
    }
    
    public Wait getWait() {
        return wait;
    }
    
    public void incrementArrivalInstances(){
        arrivalInstances++;
    }
    
    public void incrementDepartureInstances(){
        departureInstances++;
    }
    
    public abstract void computeStatistics(EntityHistory entityHistory, Server server);
    public abstract void computeGeneralStatistics(Servers servers);

}
