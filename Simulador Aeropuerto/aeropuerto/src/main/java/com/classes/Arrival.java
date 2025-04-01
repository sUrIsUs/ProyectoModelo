package com.classes;

import java.rmi.server.ServerCloneException;

public class Arrival extends Event implements Distribution {

    public Arrival(int type, double clock, Entity entity) {
        super(type, clock, entity);
    }

    @Override
    public double serviceTime(Randomizer randomizer) {
        throw new UnsupportedOperationException("Unimplemented method 'serviceTime'");
    }

    @Override
    public void planificate(FEL fel, Server server, Event event, Distribution distribution, Randomizer randomizer) {
        if(server.isBusy()){
            
        }
        else{

        }
    }
    
    
    
}
