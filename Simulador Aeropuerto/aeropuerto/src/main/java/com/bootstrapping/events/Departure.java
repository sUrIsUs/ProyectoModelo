package com.bootstrapping.events;
import com.aeropuerto.Distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.Aircraft;
import com.bootstrapping.Entity;
import com.bootstrapping.Event;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.Server;
import com.bootstrapping.Servers;
import com.bootstrapping.distribution.ServiceDuration;

public class Departure extends Event {

    private ServiceDuration serviceDuration;
    private TimeBetweenLanding timeBetweenArrival;
    public Departure(int type, double clock, Entity entity) {
        super(type, clock, entity);
        this.serviceDuration = new ServiceDuration();
        this.timeBetweenArrival = new TimeBetweenLanding();
    }

    @Override
    public void planificate(FEL fel, Servers servers, Event event, Randomizer randomizer) {
        Server server = servers.getServer();
        // Los servers estan okupas
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            fel.addEvent(new Departure(0, serviceDuration.generateTime(randomizer), arrival.getEntity()));
            server.getStatistics().incrementArrivalInstances();
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft()));
        // Estadisticas
        server.getStatistics().incrementDepartureInstances();
        server.getStatistics().getTransit().determineTransit(0); // Completar argumento!!1!
    }
    
}
