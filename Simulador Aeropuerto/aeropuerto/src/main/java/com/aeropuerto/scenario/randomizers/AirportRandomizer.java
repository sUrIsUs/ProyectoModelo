package com.aeropuerto.scenario.randomizers;

import java.util.Random;

import com.bootstrapping.Randomizer;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
public class AirportRandomizer implements Randomizer{
    private Random random;
    
    public AirportRandomizer(){
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public double next() {
        return this.random.nextDouble();
    }
}
