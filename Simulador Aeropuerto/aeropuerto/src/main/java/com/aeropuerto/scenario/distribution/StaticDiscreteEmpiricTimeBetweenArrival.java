package com.aeropuerto.scenario.distribution;

import com.engine.Randomizer;
import com.engine.distribution.staticDistribution.StaticDiscreteEmpiricEventDuration;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
public class StaticDiscreteEmpiricTimeBetweenArrival extends StaticDiscreteEmpiricEventDuration {

    public StaticDiscreteEmpiricTimeBetweenArrival(){}

    @Override
    public double generateValue(Randomizer randomizer) {
        double random = randomizer.next();
        // Si random esta en el intervalo (0;0.35]
        if (random <= 0.38) {
            return 10.0d;
        }
        // Si random esta en el intervalo (0.35;0.80]
        else if (random <= 0.8) {
            return 15.0d;
        }
        // Si random esta en el intervalo (0.80;1]
        else {
            return 17.0d;
        }
    }
}
