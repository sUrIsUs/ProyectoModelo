package com.engine.statistics;

public class Wait {
    private double totalWait = 0;
    private double minWait = 100000000;
    private double maxWait = 0;   
    private double temporalWait;

    public Wait( ) {
        this.temporalWait = 0;
    }

    public void aculuteWait(double wait){
        this.temporalWait = wait;
        this.totalWait += this.temporalWait;
        // Calculo el Wait maximo
        if(this.temporalWait > this.maxWait){
            this.maxWait = this.temporalWait; 
        }
        if(this.temporalWait != 0 && this.temporalWait < this.minWait){
            this.minWait = this.temporalWait;
        }
    }
    
    public double getTotalWait() {
        return this.totalWait;
    }
    public double getMinWait() {
        return this.minWait;
    }
    public double getMaxWait() {
        return this.maxWait;
    }

}
