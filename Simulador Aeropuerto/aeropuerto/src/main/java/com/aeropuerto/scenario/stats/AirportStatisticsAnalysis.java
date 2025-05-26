package com.aeropuerto.scenario.stats;

import com.engine.analysisStats.StatisticsAnalysis;
import com.engine.statistics.Statistics;

public class AirportStatisticsAnalysis extends StatisticsAnalysis {

    public AirportStatisticsAnalysis(int executionsQuantity){
        super(executionsQuantity);
    }

    @Override
    public void processAnalysis() {
        System.out.println("========================================================");
        System.out.println("Intervalos de confianza");
        System.out.println("========================================================");
        this.parameter.processParameter("Arribos", this.executionsQuantity, this.statisticsList, Statistics::getArrivalInstances);
        this.parameter.processParameter("Aterrizajes", this.executionsQuantity, this.statisticsList, Statistics::getDepartureInstances);
        System.out.println("========================================================");
        this.parameter.processParameter("Maximo transito", this.executionsQuantity, this.statisticsList, Statistics::getMaxTransit);
        this.parameter.processParameter("Medio transito", this.executionsQuantity, this.statisticsList, stats -> stats.getTransitTotal().getTotal() / stats.getDepartureInstances());
        this.parameter.processParameter("Min transito", this.executionsQuantity, this.statisticsList, Statistics::getMinTransit);
        System.out.println("========================================================");
        this.parameter.processParameter("Maximo espera", this.executionsQuantity, this.statisticsList, Statistics::getMaxWait);
        this.parameter.processParameter("Medio espera", this.executionsQuantity, this.statisticsList, stats -> stats.getWaitTotal().getTotal() / stats.getDepartureInstances());
        this.parameter.processParameter("Min espera", this.executionsQuantity, this.statisticsList, Statistics::getMinWait);
        System.out.println("========================================================");
        for(int i = 1; i <= this.statisticsList.get(0).getServers().getServersSize(); i++){
                int indice = i;
                this.parameter.processParameter("Durabilidad servidor " + indice + ": ", this.executionsQuantity, this.statisticsList, stats -> stats.getServers().getServerId(indice).getDurability());
                this.parameter.processParameter("Maximo cola servidor " + indice + ": ", this.executionsQuantity, this.statisticsList, stats -> stats.getServers().getServerId(indice).getQueue().getMaxSize());
                this.parameter.processParameter("Minimo cola servidor " + indice + ": ", this.executionsQuantity, this.statisticsList, stats -> stats.getServers().getServerId(indice).getQueue().getMinSize());
                this.parameter.processParameter("Total ocio proporcional al tiempo de simulación", this.executionsQuantity, this.statisticsList, stats -> (stats.getServers().getServerId(indice).getIdle().getMax() / Statistics.getSimulationLength()) * 100);
                System.out.println("========================================================");
        }
    }
    
}
