package com.engine.bootstrapping;

public final class Clock {
    public static double clock = 0;

    public Clock(){}

    protected Clock(double clock){
        this.clock = clock;
    }

    protected void setClock(double clock){
        Clock.clock = clock;
    }

    public double getClock(){
        return Clock.clock;
    }
}
