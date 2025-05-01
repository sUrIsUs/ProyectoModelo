package com.engine.distribution.dinamicDistribution;

import com.engine.Randomizer;
import java.lang.Math;

public class DinamicExponentialEventDuration extends DinamicEventDuration {

    private double []intervals;
    private double []peaksλ;
    private double notPeakλ;

    public DinamicExponentialEventDuration(double []intervals, double []peaksλ, double notPeakλ){
        // Agregar excepción de tamaño negativo y par
        this.intervals = intervals;
        this.peaksλ = peaksλ;
        this.notPeakλ = notPeakλ;
    }

    @Override
    public double generateTime(Randomizer randomizer) {
        double λ = notPeakλ;
        for(int i = 0; i < (this.intervals.length / 2); i++){
            if( (this.intervals[i*2] < (this.clock.GetClock() % 1440)) && ((this.clock.GetClock() % 1440) < this.intervals[(i*2)+1]) ){
                λ = this.peaksλ[i];
            }
        }
        return ( (-1/λ) * (Math.log(1 - randomizer.next())) );
    }
    
}
