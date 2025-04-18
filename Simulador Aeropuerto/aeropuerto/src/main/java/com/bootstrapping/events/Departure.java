package com.bootstrapping.events;
import com.aeropuerto.scenario.Aircraft;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.distribution.TimeBetweenArrival;
import com.bootstrapping.entity.Entity;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;

public class Departure extends Event {

    private ServiceDuration serviceDuration;
    private TimeBetweenArrival timeBetweenArrival;

    public Departure(int codeDeparture, double clock, Entity entity, ServiceDuration serviceDuration, TimeBetweenArrival timeBetweenArrival) {
        super(codeDeparture, clock, entity);
        this.serviceDuration = serviceDuration;
        this.timeBetweenArrival = timeBetweenArrival;
    }

    @Override
    public Server planificate(FEL fel, Servers servers, Event departure, Randomizer randomizer) {
        Server server = servers.getServerId(this.entity.getServerId());
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            arrival.getEntity().getEntityHistory().setServiceArrivalClock(this.clock);
            fel.addEvent(new Departure(0, this.clock + serviceDuration.generateTime(randomizer), arrival.getEntity(), this.serviceDuration, this.timeBetweenArrival));
            server.getStatistics().incrementArrivalInstances();
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, this.clock + timeBetweenArrival.generateTime(randomizer), new Aircraft(),this.serviceDuration, this.timeBetweenArrival)); // Evaluar si conviene hacer lo correcto, y pasar new Entity() en vez de new Aircraft()
        departure.getEntity().getEntityHistory().addDeparture(departure);
        server.getStatistics().incrementDepartureInstances();

        // Retorno el servidor utilizado para mostrar las estadísticas
        return server;
    }
    
}
