package com.bootstrapping;

import com.bootstrapping.statistics.Statistics;

public abstract class Bootsraping{

    private FEL fel;
    private Servers servers;
    private double simulationLenght;
    private double clock;
    private Statistics statistics;

    public Bootsraping(double simulationLenght) {
        this.fel = new FEL();
        this.servers = new Servers();
        this.simulationLenght = simulationLenght;
        this.clock = 0;
    }

    public abstract void startSimulation(
        // Inicializar fel

        // Empiezo simulacion
        while(simulationLenght >= clock){
            Event inminent = fel.inminent();
            inminent.planificate(fel, servers, inminent, null, null);
            System.out.println("Espera" + statistics.getWait().getTemporalWait());
        }
    );

}
