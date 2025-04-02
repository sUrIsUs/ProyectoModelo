package com.aeropuerto.scenario.codeGenerator;

import com.bootstrapping.CodeGenerator;

public class AircraftCodeGenerator implements CodeGenerator {
    private static int aircraftCoode = 1; 

    public AircraftCodeGenerator(){}

    @Override
    public int nextCode() {
        return AircraftCodeGenerator.aircraftCoode++;
    }
    
}
