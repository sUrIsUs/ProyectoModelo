package com.aeropuerto.scenario.airportEntity;

import com.aeropuerto.scenario.codeGenerator.AircraftCodeGenerator;
import com.engine.entity.Entity;

public class Aircraft extends Entity{
    
    // Idea: Atributo tamaño de avión, que indique el desgaste de pista.

    public Aircraft(){
        super(new AircraftCodeGenerator());
    }
    
}
