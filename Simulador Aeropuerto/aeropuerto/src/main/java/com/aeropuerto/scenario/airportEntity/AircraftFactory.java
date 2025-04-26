package com.aeropuerto.scenario.airportEntity;

import com.bootstrapping.entity.Entity;
import com.bootstrapping.entity.EntityFactory;

public class AircraftFactory implements EntityFactory {

    @Override
    public Entity create() {
        return new Aircraft();
    }
    
}
