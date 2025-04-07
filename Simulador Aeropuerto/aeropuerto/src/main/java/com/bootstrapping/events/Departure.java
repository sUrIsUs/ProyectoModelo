package com.bootstrapping.events;
import com.aeropuerto.Distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.Aircraft;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.entity.Entity;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;

public class Departure extends Event {

    private ServiceDuration serviceDuration;
    private TimeBetweenLanding timeBetweenArrival;
    public Departure(int type, double clock, Entity entity) {
        super(type, clock, entity);
        this.serviceDuration = new ServiceDuration();
        this.timeBetweenArrival = new TimeBetweenLanding();
    }

    @Override
    public Server planificate(FEL fel, Servers servers, Event departure, Randomizer randomizer) {
        Server server = servers.getServerId();
        // Los servers estan okupas
        if(server.getQueue().size() == 0){  
             server.setEntity(null);

        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            arrival.getEntity().getEntityHistory().setArrivalClock(this.clock);
            fel.addEvent(new Departure(0, serviceDuration.generateTime(randomizer), arrival.getEntity()));
            server.getStatistics().incrementArrivalInstances();
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft())); // Evaluar si conviene hacer lo correcto, y pasar new Entity() en vez de new Aircraft()
        
        // Estadisticas
        // Computar estadísticas con los atributos de entidad
        return server;
    }
    
}
