package com.aeropuerto.distribution;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.ServiceDuration;

public class LandingDuration extends ServiceDuration {

    public LandingDuration(){}
    
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
