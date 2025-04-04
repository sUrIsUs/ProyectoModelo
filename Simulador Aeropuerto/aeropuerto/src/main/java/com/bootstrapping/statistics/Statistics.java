package com.bootstrapping.statistics;

public class Statistics {

    // Variables para indicar la cantidad de instancias de los eventos de arribo y salidas
    private int arrivalInstances;
    private int departureInstances;
    
    private Idle idle;
    private Transit transit;
    private Wait wait;
    
    public Statistics() {
        this.arrivalInstances = 0;
        this.departureInstances = 0;
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

    


}
