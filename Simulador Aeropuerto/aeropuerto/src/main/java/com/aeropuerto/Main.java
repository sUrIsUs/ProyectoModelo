package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.randomizers.StaticRandomizer;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.bootstrapping.Bootstrapping;

import com.aeropuerto.distribution.LandingDuration;
import com.aeropuerto.distribution.TimeBetweenLanding;

public class Main {
    public static void main(String[] args) {
        Bootstrapping bootstrapping = new Bootstrapping();
        bootstrapping.startSimulation(40320d, new StaticRandomizer(), 1, new AirportStatistics(), new RunwayPrioritizer(), new LandingDuration(), new TimeBetweenLanding());
    }
}