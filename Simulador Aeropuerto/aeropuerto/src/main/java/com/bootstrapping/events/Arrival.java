package com.bootstrapping.events;

import com.aeropuerto.scenario.Aircraft;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.distribution.TimeBetweenArrival;
import com.bootstrapping.entity.Entity;
import com.bootstrapping.FEL;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;

public class Arrival extends Event {

    private ServiceDuration serviceDuration;
    private TimeBetweenArrival timeBetweenArrival;

    public Arrival(int codeArrival, double clock, Entity entity, ServiceDuration serviceDuration, TimeBetweenArrival timeBetweenArrival) {
        super(codeArrival, clock, entity);
        this.serviceDuration = serviceDuration;
        this.timeBetweenArrival = timeBetweenArrival;
    }


    @Override
    public Server planificate(FEL fel, Servers servers, Event arrival, Randomizer randomizer) {
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
            fel.addEvent(new Departure(0, this.clock + serviceDuration.generateTime(randomizer), arrival.getEntity(), this.serviceDuration, this.timeBetweenArrival));
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, this.clock + timeBetweenArrival.generateTime(randomizer), new Aircraft(), this.serviceDuration, this.timeBetweenArrival));

        // Retorna el servidor utilizado
        return server;
    }
    
}
