package com.bootstrapping;

import java.util.List;

import com.bootstrapping.codeGenerator.CodeGenerator;

public class Departure extends Event {

    public Departure(int type, double clock, Entity entity) {
        super(type, clock, entity);
    }

    @Override
    public void planificate(FEL fel, List<Server> servers, Event event, Randomizer randomizer, CodeGenerator codeGenerator) {
        
    }
    
}
