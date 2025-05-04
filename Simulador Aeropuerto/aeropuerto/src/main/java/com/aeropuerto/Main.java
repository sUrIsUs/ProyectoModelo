package com.aeropuerto;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.airportEntity.AircraftFactory;
import com.aeropuerto.scenario.distribution.StaticDiscreteEmpiricLandingDuration;
import com.aeropuerto.scenario.randomizers.AirportRandomizer;
// import com.aeropuerto.scenario.randomizers.StaticRandomizer;
import com.aeropuerto.scenario.stats.AirportStatisticsFactory;
import com.engine.bootstrapping.Bootstrapping;
import com.engine.distribution.DistributionList;
import com.engine.distribution.dinamicDistribution.DinamicExponentialEventDuration;
import com.engine.distribution.staticDistribution.StaticExponentialEventDuration;
import com.engine.distribution.staticDistribution.StaticNormalEventDuration;
import com.engine.distribution.staticDistribution.StaticUniformEventDuration;
import com.engine.exceptions.ArraysNull;
import com.engine.exceptions.NegativeNumberException;
import com.engine.exceptions.OverlappingException;

public class Main {
    public static void main(String[] args) {
        try{
            Bootstrapping bootstrapping = new Bootstrapping();
            DistributionList durabilityList = new DistributionList(), landingList = new DistributionList(), arrivalList = new DistributionList();
            double [] peaksλ = {9,9}, intervals = {9,13,20,23};
            durabilityList.addDistribution(new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04));
            landingList.addDistribution(new StaticDiscreteEmpiricLandingDuration());
            landingList.addDistribution(new StaticUniformEventDuration(10, 25));
            arrivalList.addDistribution(new DinamicExponentialEventDuration(intervals, peaksλ , 15));
            bootstrapping.startSimulation(40320d, new AirportRandomizer(), 5, 3000, durabilityList, new RunwayPrioritizer(), landingList, arrivalList, new AircraftFactory(), new AirportStatisticsFactory());
        }catch(ArraysNull e){
            System.out.println(e.getMessage());
        }catch(NegativeNumberException e){
            System.out.println("Los valores ingresados no pueden ser negativos" + e.getMessage());
        }catch(OverlappingException e){
            System.out.println("Erroneamente implementado la distribución" + e.getMessage());
        }
    }
}