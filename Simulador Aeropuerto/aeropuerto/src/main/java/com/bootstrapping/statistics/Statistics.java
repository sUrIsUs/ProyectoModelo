package com.bootstrapping.statistics;

public class Statistics {

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

}
