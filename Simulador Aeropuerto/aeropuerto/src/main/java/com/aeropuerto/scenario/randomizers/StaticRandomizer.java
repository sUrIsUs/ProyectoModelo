package com.aeropuerto.scenario.randomizers;

import java.util.Random;

import com.engine.Randomizer;

public class StaticRandomizer implements Randomizer {
    private Random random;
    

    public StaticRandomizer(){
        this.random = new Random(2);
    }

    @Override
    public double next() {
        double rand = this.random.nextDouble();
        return rand;
    }
}
