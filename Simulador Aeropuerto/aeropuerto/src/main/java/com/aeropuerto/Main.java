package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.bootstrapping.Bootstrapping;

public class Main {
    public static void main(String[] args) {
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        Bootstrapping bootstrapping = new Bootstrapping();
        bootstrapping.startSimulation(0, null, 0, null, runwayPrioritizer); 
    }
}