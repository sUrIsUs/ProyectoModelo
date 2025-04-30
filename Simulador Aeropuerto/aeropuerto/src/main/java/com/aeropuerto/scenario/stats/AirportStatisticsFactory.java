package com.aeropuerto.scenario.stats;

import com.engine.statistics.Statistics;
import com.engine.statistics.StatisticsFactory;

public class AirportStatisticsFactory implements StatisticsFactory{

    @Override
    public Statistics create() {
        return new AirportStatistics();
    }

}
