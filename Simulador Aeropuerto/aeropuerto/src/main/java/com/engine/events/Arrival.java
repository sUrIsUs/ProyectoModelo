package com.engine.events;

import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import com.engine.entity.Entity;
import com.engine.entity.EntityFactory;
import com.engine.server.Server;
import com.engine.server.Servers;

public class Arrival extends Event {

    private Distribution serviceDuration;
    private Distribution timeBetweenArrival;

    public Arrival(int codeArrival, double clock, Entity entity, Distribution serviceDuration, Distribution timeBetweenArrival) {
        super(codeArrival, clock, entity);
        this.serviceDuration = serviceDuration;
        this.timeBetweenArrival = timeBetweenArrival;
    }


    @Override
    public void planificate(FEL fel, Servers servers, Event arrival, Randomizer randomizer, EntityFactory entityFactory) {
        Server server = servers.getServer();
        this.entity.setServerId(server.getId());
        arrival.getEntity().getEntityHistory().addArrival(arrival);
        // Cuando un avión spawnea en la simu, incremento la cantidad de arribos en uno
        server.getStatistics().incrementArrivalInstances();
        // Si el server esta ocupado, agrego el arribo a la cola
        if(server.isBusy()){
            server.getQueue().add(arrival);
        }
        // Si no esta ocupado, genero su salida
        else{
            arrival.getEntity().getEntityHistory().setServiceArrivalClock(this.clock);
            server.setEntity(arrival.getEntity());
            fel.addEvent(new Departure(0, this.clock + this.serviceDuration.generateTime(randomizer), arrival.getEntity(), this.serviceDuration));
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, this.clock + this.timeBetweenArrival.generateTime(randomizer), entityFactory.create(), this.serviceDuration, this.timeBetweenArrival));
    }
    
}
