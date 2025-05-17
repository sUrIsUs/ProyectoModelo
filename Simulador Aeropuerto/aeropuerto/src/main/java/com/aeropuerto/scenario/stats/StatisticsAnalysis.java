package com.aeropuerto.scenario.stats;

import java.util.ArrayList;
import java.util.List;

import com.engine.statistics.Statistics;

public class StatisticsAnalysis {

    private double mediaMuestral;
    private double varianza;
    private double cantidadEjecuciones;
    private String [] parametros;
    private List<Statistics> statisticsList ; 

     public StatisticsAnalysis(double cantidadEjecuciones, String[] parametros) {
        this.cantidadEjecuciones = cantidadEjecuciones;
        this.parametros = parametros;
        this.statisticsList = new ArrayList<>();
    }

    public void addStatistics(Statistics statistics){
        statisticsList.add(statistics);
    } 
}
