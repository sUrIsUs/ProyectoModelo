package com.bootstrapping.statistics;

public class Wait {
    private static double totalWait;
    private static double minWait;
    private static double maxWait;   
    private double temporalWait;

    public Wait( ) {
        this.temporalWait = 0;
    }

    public void determineWait(double wait){
        this.temporalWait = wait;
        totalWait += this.temporalWait;
        // Calculo el Waito maximo
        if(this.temporalWait > maxWait){
            maxWait = this.temporalWait; 
        }
        else if(this.temporalWait != 0 && this.temporalWait < minWait){
            minWait = this.temporalWait;
        }
    }
    
    public static double getTotalWait() {
        return totalWait;
    }
    public static double getMinWait() {
        return minWait;
    }
    public static double getMaxWait() {
        return maxWait;
    }
    public double getTemporalWait() {
        return temporalWait;
    }
}
