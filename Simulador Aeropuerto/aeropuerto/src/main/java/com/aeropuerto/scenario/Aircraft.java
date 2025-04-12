package com.aeropuerto.scenario;

import com.aeropuerto.scenario.codeGenerator.AircraftCodeGenerator;
import com.bootstrapping.entity.Entity;

public class Aircraft extends Entity{
    
    // Idea: Atributo tamaño de avión, que indique el desgaste de pista.

    public Aircraft(){
        super(new AircraftCodeGenerator());
    }
    
}
