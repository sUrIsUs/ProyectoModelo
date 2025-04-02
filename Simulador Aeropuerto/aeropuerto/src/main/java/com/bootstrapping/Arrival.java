package com.bootstrapping;

import java.util.List;

import com.bootstrapping.codeGenerator.ArrivalCodeGenerator;
import com.bootstrapping.codeGenerator.CodeGenerator;

public class Arrival extends Event {

    private Distribution arrivalDistribution;
    private Distribution timeBetweenArrival;
    public Arrival(int type, double clock, Entity entity) {
        super(type, clock, entity);
    }


    @Override
    public void planificate(FEL fel, List<Server> servers, Event event, Randomizer randomizer, CodeGenerator codeGenerator) {
        Server server = servers.get(0);
        // Si el server esta ocupado, agrego el arribo a la cola
        if(server.isBusy()){
            server.getQueue().add(event);
        }
        // Si no esta ocupado, genero su salida
        else{
            server.setEntity(this.entity);
            fel.addEvent(new Departure(0, arrivalDistribution.generateTime(randomizer), this.entity));
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Entity(new ArrivalCodeGenerator().nextCode())));
    }
    
    
    
}
