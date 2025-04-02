package com.aeropuerto.scenario.codeGenerator;

import com.bootstrapping.CodeGenerator;

public class ArrivalCodeGenerator implements CodeGenerator {
    private static int code = 0;

    public ArrivalCodeGenerator(){
    }

    @Override
    public int nextCode() {
        return code++;
    }
    
}
