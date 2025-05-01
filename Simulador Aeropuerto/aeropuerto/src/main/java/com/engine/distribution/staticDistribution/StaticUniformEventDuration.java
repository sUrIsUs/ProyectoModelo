package com.engine.distribution.staticDistribution;

import com.engine.Randomizer;
import com.engine.distribution.Distribution;

public class StaticUniformEventDuration implements Distribution {
    
    private double a;
    private double b;

    public StaticUniformEventDuration(double a, double b){
        this.a = a;
        this.b = b;
    }
    
    @Override
    public double generateTime(Randomizer randomizer) {
        return this.a + (this.b - this.a) * randomizer.next();
    }
    
}
