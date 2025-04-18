package com.bootstrapping.statistics;

public class Wait {
    private static double totalWait = 0;
    private static double minWait = 100000000;
    private static double maxWait = 0;   
    private double temporalWait;

    public Wait( ) {
        this.temporalWait = 0;
    }

    public void aculuteWait(double wait){
        this.temporalWait = wait;
        Wait.totalWait += this.temporalWait;
        // Calculo el Wait maximo
        if(this.temporalWait > Wait.maxWait){
            Wait.maxWait = this.temporalWait; 
        }
        if(this.temporalWait != 0 && this.temporalWait < Wait.minWait){
            Wait.minWait = this.temporalWait;
        }
    }
    
    public static double getTotalWait() {
        return Wait.totalWait;
    }
    public static double getMinWait() {
        return Wait.minWait;
    }
    public static double getMaxWait() {
        return Wait.maxWait;
    }

}
