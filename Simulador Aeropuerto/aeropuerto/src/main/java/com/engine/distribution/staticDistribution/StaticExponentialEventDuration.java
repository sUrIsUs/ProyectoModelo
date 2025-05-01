package com.engine.distribution.staticDistribution;

import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import java.lang.Math;

public class StaticExponentialEventDuration implements Distribution {

    private double λ;

    public StaticExponentialEventDuration(double λ){
        this.λ = λ;
    }

    @Override
    public double generateTime(Randomizer randomizer) {
        return (-1 / λ) * (Math.log(1 - randomizer.next()));
    }
    
}
