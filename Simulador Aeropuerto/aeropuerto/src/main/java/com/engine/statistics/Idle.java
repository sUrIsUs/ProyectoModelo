package com.engine.statistics;

public class Idle {
   
    private double totalIdle = 0; 
    private double minIdle = 100000000;
    private double maxIdle = 0;
    private double temporalIdle;
    
    public Idle() {
        this.temporalIdle = 0;
    }

    public void acumulateIdle(double idle){
        this.temporalIdle = (idle > 0)? idle : 0;
        this.totalIdle += this.temporalIdle;
        // Calculo el ocio maximo
        if(this.temporalIdle > maxIdle){
            this.maxIdle = this.temporalIdle; 
        }
        if(this.temporalIdle != 0 && this.temporalIdle < minIdle){
            this.minIdle = this.temporalIdle;
        }
    }
    
    public double getTotalIdle() {
        return this.totalIdle;
    }
    public double getMinIdle() {
        return this.minIdle;
    }
    public double getMaxIdle() {
        return this.maxIdle;
    }
    
}
