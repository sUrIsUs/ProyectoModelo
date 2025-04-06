package com.bootstrapping.statistics;

public class Statistics {

    // Variables para indicar la cantidad de instancias de los eventos de arribo y salidas
    private static int arrivalInstances = 0;
    private static int departureInstances = 0;
    
    private Idle idle;
    private Transit transit;
    private Wait wait;
    private double arrivalClock;
    private double departureClock;
    private double serviceClock;
    
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
    
    public double getArrivalClock() {
        return arrivalClock;
    }
    
    public void setArrivalClock(double arrivalClock) {
        this.arrivalClock = arrivalClock;
    }
    
    public double getDepartureClock() {
        return departureClock;
    }
    
    public void setDepartureClock(double departureClock) {
        this.departureClock = departureClock;
    }
    
    public double getServiceClock() {
        return serviceClock;
    }
    
    public void setServiceClock(double serviceClock) {
        this.serviceClock = serviceClock;
    }
}
