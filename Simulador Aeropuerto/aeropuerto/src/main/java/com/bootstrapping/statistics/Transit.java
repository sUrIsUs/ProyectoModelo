package com.bootstrapping.statistics;

public class Transit {
    private static double totalTransit = 0;
    private static double minTransit = 100000000;
    private static double maxTransit = 0;
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
        else if(this.temporalTransit != 0 && this.temporalTransit < minTransit){
            minTransit = this.temporalTransit;
        }
    }

    public static double getTotalTransit() {
        return totalTransit;
    }

    public static double getMinTransit() {
        return minTransit;
    }

    public static double getMaxTransit() {
        return maxTransit;
    }

    public double getTemporalTransit() {
        return temporalTransit;
    }
    
}
