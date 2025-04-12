package com.bootstrapping.server;

import com.bootstrapping.CodeGenerator;

public class ServerCodeGenerator implements CodeGenerator {
    private static int serverInstances = 1; 

    public ServerCodeGenerator(){}

    @Override
    public int nextCode() {
        return ServerCodeGenerator.serverInstances++;
    }
    
}
