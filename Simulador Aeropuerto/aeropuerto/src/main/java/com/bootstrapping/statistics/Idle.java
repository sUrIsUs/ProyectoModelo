package com.bootstrapping.statistics;

public class Idle {
   
    private static double totalIdle = 0; 
    private static double minIdle = 100000000;
    private static double maxIdle = 0;
    private double temporalIdle;
    
    public Idle() {
        this.temporalIdle = 0;
    }

    public void acumulateIdle(double idle){
        this.temporalIdle = (idle > 0)? idle : 0;
        Idle.totalIdle += this.temporalIdle;
        // Calculo el ocio maximo
        if(this.temporalIdle > maxIdle){
            Idle.maxIdle = this.temporalIdle; 
        }
        if(this.temporalIdle != 0 && this.temporalIdle < minIdle){
            Idle.minIdle = this.temporalIdle;
        }
    }
    
    public static double getTotalIdle() {
        return Idle.totalIdle;
    }
    public static double getMinIdle() {
        return Idle.minIdle;
    }
    public static double getMaxIdle() {
        return Idle.maxIdle;
    }
    
}
