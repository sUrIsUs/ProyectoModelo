package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.distribution.LandingDuration;
import com.aeropuerto.scenario.distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.randomizers.AirportRandomizer;
// import com.aeropuerto.scenario.randomizers.StaticRandomizer;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.bootstrapping.Bootstrapping;

public class Main {
    public static void main(String[] args) {
        Bootstrapping bootstrapping = new Bootstrapping();
        bootstrapping.startSimulation(40320d, new AirportRandomizer(), 1, new AirportStatistics(), new RunwayPrioritizer(), new LandingDuration(), new TimeBetweenLanding());
    }
}