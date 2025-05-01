package com.engine.bootstrapping;

public class Clock {
    public static double clock = 0;

    public Clock(){}

    protected void setClock(double clock){
        Clock.clock = clock;
    }

    public double GetClock(){
        return Clock.clock;
    }
}
