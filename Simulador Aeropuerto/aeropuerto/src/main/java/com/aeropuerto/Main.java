package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.airportEntity.AircraftFactory;
import com.aeropuerto.scenario.distribution.LandingDuration;
import com.aeropuerto.scenario.distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.randomizers.AirportRandomizer;
// import com.aeropuerto.scenario.randomizers.StaticRandomizer;
import com.aeropuerto.scenario.stats.AirportStatisticsFactory;
import com.bootstrapping.Bootstrapping;

public class Main {
    public static void main(String[] args) {
        Bootstrapping bootstrapping = new Bootstrapping();
        bootstrapping.startSimulation(40320d, new AirportRandomizer(), 3, new AirportStatisticsFactory(), new RunwayPrioritizer(), new LandingDuration(), new TimeBetweenLanding(), new AircraftFactory());
    }
}