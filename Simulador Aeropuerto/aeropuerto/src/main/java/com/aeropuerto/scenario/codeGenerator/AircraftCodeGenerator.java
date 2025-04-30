package com.aeropuerto.scenario.codeGenerator;

import com.engine.CodeGenerator;

public class AircraftCodeGenerator implements CodeGenerator {
    private static int aircraftCode = 1; 

    public AircraftCodeGenerator(){}

    @Override
    public int nextCode() {
        return AircraftCodeGenerator.aircraftCode++;
    }

}
