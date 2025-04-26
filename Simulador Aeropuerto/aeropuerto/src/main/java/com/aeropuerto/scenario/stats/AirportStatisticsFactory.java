package com.aeropuerto.scenario.stats;

import com.bootstrapping.statistics.Statistics;
import com.bootstrapping.statistics.StatisticsFactory;

public class AirportStatisticsFactory implements StatisticsFactory{

    @Override
    public Statistics create() {
        return new AirportStatistics();
    }

}
