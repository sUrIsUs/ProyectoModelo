package com.bootstrapping;

public abstract class Bootsraping{

    private FEL fel;
    private Servers servers;
    private double simulationLenght;
    private double clock;
    private BootsrapingStatistics bootsrapingStatistics;

    public Bootsraping(double simulationLenght) {
        this.fel = new FEL();
        this.servers = new Servers();
        this.simulationLenght = simulationLenght;
        this.clock = 0;
    }

    public abstract void startSimulation();

}
