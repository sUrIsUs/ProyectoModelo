package com.bootstrapping.statistics;

public class Idle {
   
    private static double totalIdle;
    private static double minIdle;
    private static double maxIdle;
    private double temporalIdle;
    
    public Idle() {
        this.temporalIdle = 0;
    }

    public void acumulateIdle(double idle){
        this.temporalIdle = idle;
        totalIdle += this.temporalIdle;
        // Calculo el Idleo maximo
        if(this.temporalIdle > maxIdle){
            maxIdle = this.temporalIdle; 
        }
        else if(this.temporalIdle != 0 && this.temporalIdle < minIdle){
            minIdle = this.temporalIdle;
        }
    }
    
    public static double getTotalIdle() {
        return totalIdle;
    }
    public static double getMinIdle() {
        return minIdle;
    }
    public static double getMaxIdle() {
        return maxIdle;
    }
    public double getTemporalIdle() {
        return temporalIdle;
    }
    
}
