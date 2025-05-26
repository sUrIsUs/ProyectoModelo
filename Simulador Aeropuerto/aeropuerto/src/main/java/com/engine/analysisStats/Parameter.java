package com.engine.analysisStats;

import java.util.List;
import java.util.function.ToDoubleFunction;

import com.engine.statistics.Statistics;

public class Parameter {
    protected  double meanSample;
    protected double standardDeviationSample;

    public Parameter(){
        this.meanSample = 0;
        this.standardDeviationSample = 0;
    }
    
    public void processParameter(String label, int executionsQuantity, List<Statistics> statisticsList, ToDoubleFunction<Statistics> mapper){
        this.meanSample = statisticsList.stream().mapToDouble(mapper).average().orElse(0.0);
        this.standardDeviationSample = statisticsList.stream()
                                .mapToDouble(mapper)
                                .map(val -> Math.pow(val - this.meanSample,2))
                                .average()
                                .orElse(0.0);

        this.standardDeviationSample = Math.sqrt(this.standardDeviationSample);
        
        calculateConfidenceInterval(label, executionsQuantity);
        // System.out.println("Media de medias: " + this.meanSample);
        // System.out.println("Desviacion estandar: "+ this.standardDeviationSample);
        this.meanSample = 0;
        this.standardDeviationSample = 0;
    }


    private void calculateConfidenceInterval(String label, int executionsQuantity){
        System.out.println("Intervalo de confianza para " + label 
                            + ": ["
                            + (this.meanSample - (1.96 * (this.standardDeviationSample / Math.sqrt(executionsQuantity))))
                            + "; "
                            + (this.meanSample + (1.96 * (this.standardDeviationSample / Math.sqrt(executionsQuantity))))
                            + "]");
    }
}
