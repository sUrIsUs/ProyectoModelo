package com.engine.statistics;

import com.engine.entity.EntityHistory;
import com.engine.server.Server;
import com.engine.server.Servers;

/**
 * Almacena estadísticas tránsito para cada servidor, y de la espera de las entidades, ocio y durabilidad de los servidores, cantidad de arribos y salidas
 * Realizará cálculos utilizando los métodos para las clases Transit y Wait
 * Deberá implementar los métodos (1) computeStatistics(), que realiza los cálculos de cada estadística que desee almacenarse de cada servidor y entidad (ej. espera, tránsito), y (2) computeGeneralStatistics, que realiza los cálculos y/o muestra las estadísticas de la simulación que al usuario le interese (ej. tiempos máximos, medios, mínimos)
 */
public abstract class Statistics {

    // Variables para indicar la cantidad de instancias de los eventos de arribo y salidas
    private int arrivalInstances = 0;
    private int departureInstances = 0;
    
    private final Transit transitTotal;
    private final Wait waitTotal;
    private static double simulationLength;
    private Servers servers;

    //
    public Statistics() {
        this.transitTotal = new Transit();
        this.waitTotal = new Wait();
    }
    
    public int getArrivalInstances() {
        return arrivalInstances;
    }
    
    public int getDepartureInstances() {
        return departureInstances;
    }
    
    // Getters Transit
    public Transit getTransitTotal() {
        return transitTotal;
    }

    public double getTransitMedium(){
        return this.transitTotal.getTotal() / this.departureInstances;
    }

    public double getMaxTransit(){
        return this.transitTotal.getMax();
    }

    public double getMinTransit(){
        return this.transitTotal.getMin();
    }
    
    // Getters Wait
    public Wait getWaitTotal() {
        return waitTotal;
    }

    public double getWaitMedium(){
        return this.waitTotal.getTotal() / this.departureInstances;
    }

    public double getMaxWait(){
        return this.waitTotal.getMax();
    }

    public double getMinWait(){
        return this.waitTotal.getMin();
    }
    
    public void incrementArrivalInstances(){
        this.arrivalInstances++;
    }
    
    public void incrementDepartureInstances(){
        this.departureInstances++;
    }

    public static double getSimulationLength() {
        return Statistics.simulationLength;
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
