package com.engine.analysisStats;

import java.util.ArrayList;
import java.util.List;

import com.engine.statistics.Statistics;

public abstract class StatisticsAnalysis {

    protected int executionsQuantity;
    protected List<Statistics> statisticsList;
    protected Parameter parameter;

     public StatisticsAnalysis(int executionsQuantity) {
        this.executionsQuantity = executionsQuantity;
        this.statisticsList = new ArrayList<>();
        this.parameter = new Parameter();
    }

    public void addStatistics(Statistics statistics){
        statisticsList.add(statistics);
    }

    public abstract void processAnalysis();

}
