package com.engine.server;

import com.engine.CodeGenerator;

public class ServerCodeGenerator implements CodeGenerator {
    private static int serverInstances; 

    public ServerCodeGenerator(){
        ServerCodeGenerator.serverInstances = 1;
    }

    @Override
    public int nextCode() {
        return ServerCodeGenerator.serverInstances++;
    }
    
}
