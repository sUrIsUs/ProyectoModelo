package com.engine.distribution.dinamicDistribution;

import com.engine.bootstrapping.Clock;
import com.engine.distribution.Distribution;

public abstract class DinamicEventDuration implements Distribution {
    
    protected Clock clock;

    public DinamicEventDuration(){
        this.clock = new Clock();
    }
}
