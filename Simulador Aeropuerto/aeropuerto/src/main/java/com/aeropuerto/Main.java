package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.airportEntity.AircraftFactory;
import com.aeropuerto.scenario.distribution.StaticUniformLandingDuration;
import com.aeropuerto.scenario.distribution.StaticUniformTimeBetweenLanding;
import com.aeropuerto.scenario.randomizers.AirportRandomizer;
// import com.aeropuerto.scenario.randomizers.StaticRandomizer;
import com.aeropuerto.scenario.stats.AirportStatisticsFactory;
import com.engine.bootstrapping.Bootstrapping;

public class Main {
    public static void main(String[] args) {
        Bootstrapping bootstrapping = new Bootstrapping();
        bootstrapping.startSimulation(40320d, new AirportRandomizer(), 3, new AirportStatisticsFactory(), new RunwayPrioritizer(), new StaticUniformLandingDuration(), new StaticUniformTimeBetweenLanding(), new AircraftFactory());
    }
}