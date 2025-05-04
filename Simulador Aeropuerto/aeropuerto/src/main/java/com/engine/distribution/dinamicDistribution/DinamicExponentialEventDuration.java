package com.engine.distribution.dinamicDistribution;

import com.engine.Randomizer;
import com.engine.exceptions.ArraysNull;
import com.engine.exceptions.NegativeNumberException;
import com.engine.exceptions.OverlappingException;

import java.lang.Math;

public class DinamicExponentialEventDuration extends DinamicEventDuration {

    private double []intervals;
    private double []peaksλ;
    private double notPeakλ;

    public DinamicExponentialEventDuration(double []intervals, double []peaksλ, double notPeakλ) throws ArraysNull,NegativeNumberException, OverlappingException{

        super();
        this.intervals = intervals;
        this.peaksλ = peaksλ;
        this.notPeakλ = notPeakλ;     
        try {
            determineException();
        } catch(ArraysNull e){
            System.out.println(e.getMessage());
            throw e;
        }catch(NegativeNumberException e){
            System.out.println("Los valores ingresados no pueden ser negativos" + e.getMessage());
            throw e;
        }catch(OverlappingException e){
            System.out.println("Erroneamente implementado la distribución" + e.getMessage());
            throw e;
        }
        intervalsDivider(intervals, peaksλ);

    }

    @Override
    public double generateValue(Randomizer randomizer) {
        double λ = notPeakλ;
        for(int i = 0; i < (this.intervals.length / 2); i++){
            if((this.intervals[i*2] < (this.clock.getClock() % 1440)) && ((this.clock.getClock() % 1440) < this.intervals[(i*2)+1]) ){
                λ = this.peaksλ[i];
            }
        }
        return ( (-λ) * (Math.log(1 - randomizer.next())) );
    }

    private void intervalsDivider(double [] intervals, double [] peaksλ){
        double [] intervalsR = new double[intervals.length + 1];
        double [] peaksλR = new double[peaksλ.length];
        Boolean flag = false;
        for(int i = 0; i < intervals.length / 2; i++){
            intervalsR[i*2] = intervals[i*2];
            intervalsR[i*2 + 1] = intervals[i*2 + 1];
            peaksλR[i] = peaksλ[i];
            if(intervals[i*2] > intervals[i*2 + 1]){
                double aux = intervals[i*2 + 1];
                intervals[i*2 + 1] = 24;
                intervalsR[intervals.length] = 0;
                intervalsR[intervals.length + 1] = aux;
                peaksλR[peaksλ.length] = peaksλ[i];
                flag = true;
            }
        }
        if(flag){
            this.intervals = intervalsR;
            this.peaksλ = peaksλR;
        }
        else{
            this.intervals = intervals;
            this.peaksλ = peaksλ;
        }
    }

    private void determineException() throws ArraysNull, NegativeNumberException, OverlappingException{

        if(this.intervals == null || this.peaksλ == null){
            throw new ArraysNull("No se aceptan intervalos nulos");
        }
        for(int i = 0; i < this.intervals.length/2 ; i++){
            if(this.intervals[i*2] < 0 || this.intervals[i*2 + 1] < 0){
                throw new NegativeNumberException("Los intervalos no pueden ser negativos");
            }
            for(int j = i + 1; j < this.intervals.length/2 ; j++){
                if(
                    (this.intervals[i*2] <= this.intervals[j*2] && this.intervals[j*2] <= this.intervals[i*2 + 1]) ||
                (this.intervals[i*2] <= this.intervals[j*2 + 1] && this.intervals[j*2 + 1] <= this.intervals[i*2 + 1]))
                {
                    throw new OverlappingException("Los intervalos no pueden estar solapados");
                }
            }
        }

    }

    
}
