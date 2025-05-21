package com.engine.statistics;

import com.engine.entity.EntityHistory;
import com.engine.server.Server;
import com.engine.server.Servers;

/**
 * Esta clase almacena estadísticas de ocio y tránsito para cada servidor, y de la espera de las entidades
 * Realizará cálculos utilizando los métodos para las clases Idle, Transit y Wait
 * Deberá implementar los métodos (1) computeStatistics(), que realiza los cálculos de cada estadística que desee almacenarse de cada servidor y entidad (ej. ocio, espera, tránsito), y (2) computeGeneralStatistics, que realiza los cálculos y/o muestra las estadísticas de la simulación que al usuario le interese (ej. tiempos máximos, medios, mínimos)
 */
public abstract class Statistics {

    // Variables para indicar la cantidad de instancias de los eventos de arribo y salidas
    private int arrivalInstances = 0;
    private int departureInstances = 0;
    
    private Idle idleTotal;
    private Transit transitTotal;
    private Wait waitTotal;
    private static double simulationLength;
    private Servers servers;

    
    public Statistics() {
        this.idleTotal = new Idle();
        this.transitTotal = new Transit();
        this.waitTotal = new Wait();
    }
    // Variables estáticas
    
    public int getArrivalInstances() {
        return arrivalInstances;
    }
    
    public int getDepartureInstances() {
        return departureInstances;
    }
    
    public Idle getIdleTotal() {
        return idleTotal;
    }

    public double getMaxIdle(){
        return this.idleTotal.getMax();
    }

    public double getMinIdle(){
        return this.idleTotal.getMin();
    }
    
    public Transit getTransitTotal() {
        return transitTotal;
    }

    public double getMaxTransit(){
        return this.transitTotal.getMax();
    }

    public double getMinTransit(){
        return this.transitTotal.getMin();
    }
    
    public Wait getWaitTotal() {
        return waitTotal;
    }

    public double getMaxWait(){
        return this.waitTotal.getMax();
    }

    public double getMinWait(){
        return this.waitTotal.getMin();
    }
    
    public void incrementArrivalInstances(){
        arrivalInstances++;
    }
    
    public void incrementDepartureInstances(){
        departureInstances++;
    }

    public static double getSimulationLength() {
        return simulationLength;
    }

    public static void setSimulationLength(double simulationLength) {
        Statistics.simulationLength = simulationLength;
    }
    public void setServers(Servers servers){
        this.servers = servers;
    }

    public Servers getServers(){
        return this.servers;
    } 

    public abstract void computeStatistics(EntityHistory entityHistory, Server server);
    public abstract void processGeneralStatistics();

    
    
}
