package com.aeropuerto.scenario.randomizers;

import java.util.Random;

import com.bootstrapping.Randomizer;

public class StaticRandomizer implements Randomizer {
    private Random random;

    public StaticRandomizer(){
        this.random = new Random(4);
    }

    @Override
    public double next() {
        return this.random.nextDouble();
    }
}
