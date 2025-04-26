package com.bootstrapping.statistics;

public class Transit {
    private double totalTransit = 0;
    private double minTransit = 100000000;
    private double maxTransit = 0;
    private double temporalTransit;
    
    public Transit() {
        this.temporalTransit = 0;
    }

    public void acumulateTransit(double transit){
        this.temporalTransit = transit;
        totalTransit += this.temporalTransit;
        // Calculo el transito maximo
        if(this.temporalTransit > maxTransit){
            maxTransit = this.temporalTransit; 
        }
        if(this.temporalTransit != 0 && this.temporalTransit < minTransit){
            minTransit = this.temporalTransit;
        }
    }

    public double getTotalTransit() {
        return this.totalTransit;
    }

    public double getMinTransit() {
        return this.minTransit;
    }

    public double getMaxTransit() {
        return this.maxTransit;
    }
    
}
