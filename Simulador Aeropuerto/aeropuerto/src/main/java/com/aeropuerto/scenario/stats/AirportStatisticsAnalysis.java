package com.aeropuerto.scenario.stats;

import com.engine.analysisStats.StatisticsAnalysis;
import com.engine.statistics.Statistics;

public class AirportStatisticsAnalysis extends StatisticsAnalysis {

    public AirportStatisticsAnalysis(int executionsQuantity){
        super(executionsQuantity);
    }

    @Override
    public void processAnalysis() {
        this.parameter.processParameter("Arribos", this.executionsQuantity, this.statisticsList, Statistics::getArrivalInstances);
        this.parameter.processParameter("Salidas", this.executionsQuantity, this.statisticsList, Statistics::getDepartureInstances);
        this.parameter.processParameter("Maximo ocio", this.executionsQuantity, this.statisticsList, Statistics::getMaxIdle);
        this.parameter.processParameter("Min ocio", this.executionsQuantity, this.statisticsList, Statistics::getMinIdle);
        this.parameter.processParameter("Maximo transito", this.executionsQuantity, this.statisticsList, Statistics::getMaxTransit);
        this.parameter.processParameter("Min transito", this.executionsQuantity, this.statisticsList, Statistics::getMinTransit);
        this.parameter.processParameter("Maximo espera", this.executionsQuantity, this.statisticsList, Statistics::getMaxWait);
        this.parameter.processParameter("Min espera", this.executionsQuantity, this.statisticsList, Statistics::getMinWait);
        this.parameter.processParameter("Min espera", this.executionsQuantity, this.statisticsList, Statistics::getMinWait);

    }
    
}
