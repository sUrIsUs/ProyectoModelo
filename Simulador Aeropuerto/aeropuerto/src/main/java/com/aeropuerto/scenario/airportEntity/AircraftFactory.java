package com.aeropuerto.scenario.airportEntity;

import com.engine.entity.Entity;
import com.engine.entity.EntityFactory;

public class AircraftFactory implements EntityFactory {

    @Override
    public Entity create() {
        return new Aircraft();
    }
    
}
