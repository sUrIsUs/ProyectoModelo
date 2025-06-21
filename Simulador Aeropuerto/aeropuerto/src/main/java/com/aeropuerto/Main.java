package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.airportEntity.AircraftFactory;
import com.aeropuerto.scenario.distribution.StaticDiscreteEmpiricLandingDuration;
import com.aeropuerto.scenario.randomizers.AirportRandomizer;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.aeropuerto.scenario.stats.AirportStatisticsAnalysis;
import com.engine.bootstrapping.Bootstrapping;
import com.engine.distribution.DistributionAccumulator;
import com.engine.distribution.DistributionList;
import com.engine.distribution.dinamicDistribution.DinamicExponentialEventDuration;
import com.engine.distribution.staticDistribution.StaticExponentialEventDuration;
import com.engine.distribution.staticDistribution.StaticNormalEventDuration;
import com.engine.distribution.staticDistribution.StaticUniformEventDuration;
import com.engine.events.ArrivalFactory;
import com.engine.exceptions.ArraysNull;
import com.engine.exceptions.NegativeNumberException;
import com.engine.exceptions.OverlappingException;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            Bootstrapping bootstrapping;
            DistributionAccumulator durabilityList = new DistributionAccumulator(), landingList = new DistributionAccumulator(), arrivalList = new DistributionAccumulator();
            double [] peaksλ = {9,9}, intervals = {9,13,20,23};
            durabilityList.accumulateDistribution(new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04));
            landingList.accumulateDistribution(new StaticDiscreteEmpiricLandingDuration());
            landingList.accumulateDistribution(new StaticUniformEventDuration(10, 25));
            arrivalList.accumulateDistribution(new DinamicExponentialEventDuration(intervals, peaksλ , 15));
            DistributionList distributionList = new DistributionList();
            distributionList.addDistribution(landingList);
            distributionList.addDistribution(arrivalList);
            int executionsQuantity = 50;
            AirportStatisticsAnalysis airportStatisticsAnalysis = new AirportStatisticsAnalysis(executionsQuantity);
            for (int i = 0; i < executionsQuantity; i ++){
                bootstrapping = new Bootstrapping(new AirportStatistics());
                airportStatisticsAnalysis.addStatistics(
                    bootstrapping.startSimulation(40320d, new AirportRandomizer(), 5, 3000, durabilityList, new RunwayPrioritizer(), distributionList, new AircraftFactory(), new ArrivalFactory())
                );
            }
            airportStatisticsAnalysis.processAnalysis();
        }catch(ArraysNull e){
            System.out.println(e.getMessage());
        }catch(NegativeNumberException e){
            System.out.println("Los valores ingresados no pueden ser negativos" + e.getMessage());
        }catch(OverlappingException e){
            System.out.println("Erroneamente implementado la distribución" + e.getMessage());
        }
    }
}