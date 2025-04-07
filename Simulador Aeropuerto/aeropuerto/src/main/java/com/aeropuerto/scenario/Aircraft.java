package com.aeropuerto.scenario;

import com.aeropuerto.scenario.codeGenerator.AircraftCodeGenerator;
import com.bootstrapping.entity.Entity;

public class Aircraft extends Entity{

    public Aircraft(){
        super(new AircraftCodeGenerator());
    }
    
}
