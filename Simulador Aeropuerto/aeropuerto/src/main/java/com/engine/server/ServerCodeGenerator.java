package com.engine.server;

import com.engine.CodeGenerator;

public class ServerCodeGenerator implements CodeGenerator {
    private static int serverInstances = 1; 

    public ServerCodeGenerator(){}

    @Override
    public int nextCode() {
        return ServerCodeGenerator.serverInstances++;
    }
    
}
