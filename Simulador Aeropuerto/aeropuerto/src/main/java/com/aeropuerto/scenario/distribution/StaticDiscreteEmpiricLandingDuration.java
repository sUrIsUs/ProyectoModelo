package com.aeropuerto.scenario.distribution;
import com.engine.Randomizer;
import com.engine.distribution.staticDistribution.StaticDiscreteEmpiricEventDuration;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
public class StaticDiscreteEmpiricLandingDuration extends StaticDiscreteEmpiricEventDuration {

    public StaticDiscreteEmpiricLandingDuration(){}
    
    @Override
    public double generateTime(Randomizer randomizer) {
        double random = randomizer.next();
        // Si random esta en el intervalo (0;0.38]
        if(random <= 0.38){
            return 8.0d;
        }
        // Si random esta en el intervalo (0.38;0.70]
        else if(random <= 0.70){
            return 10.0d;
        }
        // Si random esta en el intervalo (0.70;0.80]
        else if(random <= 0.8){
            return 13.0d;
        }
        // Si random esta en el intervalo (0.80;1]
        else {
            return 15.0d;
        }
    }

}
