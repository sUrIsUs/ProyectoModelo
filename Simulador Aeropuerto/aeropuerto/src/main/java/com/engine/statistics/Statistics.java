package com.engine.statistics;

import com.engine.entity.EntityHistory;
import com.engine.server.Server;

/**
 * Esta clase almacena estadísticas de ocio y tránsito para cada servidor, y de la espera de las entidades
 * Realizará cálculos utilizando los métodos para las clases Idle, Transit y Wait
 * Deberá implementar los métodos (1) computeStatistics(), que realiza los cálculos de cada estadística que desee almacenarse de cada servidor y entidad (ej. ocio, espera, tránsito), y (2) computeGeneralStatistics, que realiza los cálculos y/o muestra las estadísticas de la simulación que al usuario le interese (ej. tiempos máximos, medios, mínimos)
 */
public abstract class Statistics {

    // Variables para indicar la cantidad de instancias de los eventos de arribo y salidas
    private int arrivalInstances = 0;
    private int departureInstances = 0;
    
    private Idle idle;
    private Transit transit;
    private Wait wait;
    private static Idle idleTotal = new Idle();
    private static Transit transitTotal = new Transit();
    private static Wait waitTotal = new Wait(); 
    private static double simulationLength;

    
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
    
    public static Idle getIdleTotal() {
        return idleTotal;
    }
    
    public static void setIdleTotal(Idle idleTotal) {
        Statistics.idleTotal = idleTotal;
    }
    
    public static Transit getTransitTotal() {
        return transitTotal;
    }
    
    public static void setTransitTotal(Transit transitTotal) {
        Statistics.transitTotal = transitTotal;
    }
    
    public static Wait getWaitTotal() {
        return waitTotal;
    }
    public static void setWaitTotal(Wait waitTotal) {
        Statistics.waitTotal = waitTotal;
    }
    
    public static double getSimulationLength() {
        return simulationLength;
    }

    public static void setSimulationLength(double simulationLength) {
        Statistics.simulationLength = simulationLength;
    }

    public abstract void computeStatistics(EntityHistory entityHistory, Server server);
    public abstract void processServerStatistics(Server server);
    public abstract void processGeneralStatistics(int arrivals, int departures);
    
    
}
