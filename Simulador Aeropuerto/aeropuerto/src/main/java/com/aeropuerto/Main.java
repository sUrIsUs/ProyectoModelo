package com.aeropuerto;

import com.aeropuerto.Distribution.LandingDuration;
import com.aeropuerto.Distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.randomizers.StaticRandomizer;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.bootstrapping.Bootstrapping;

public class Main {
    public static void main(String[] args) {
        Bootstrapping bootstrapping = new Bootstrapping();
        bootstrapping.startSimulation(60, new StaticRandomizer(), 1, new AirportStatistics(), new RunwayPrioritizer(), new LandingDuration(), new TimeBetweenLanding()); 
    }
}