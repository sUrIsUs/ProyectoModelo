package com.engine.distribution.staticDistribution;

import com.engine.Randomizer;
import com.engine.distribution.Distribution;

public class StaticExponentialEventDuration implements Distribution {

    private final double λ;

    public StaticExponentialEventDuration(double λ){
        this.λ = λ;
    }

    @Override
    public double generateValue(Randomizer randomizer) {
        return (-1 / λ) * (Math.log(1 - randomizer.next()));
    }
    
}
