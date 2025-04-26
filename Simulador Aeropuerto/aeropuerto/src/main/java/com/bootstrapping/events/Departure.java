package com.bootstrapping.events;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.distribution.TimeBetweenArrival;
import com.bootstrapping.entity.Entity;
import com.bootstrapping.entity.EntityFactory;
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
    public Server planificate(FEL fel, Servers servers, Event departure, Randomizer randomizer, EntityFactory entityFactory) {
        Server server = servers.getServerId(this.entity.getServerId());
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            server.setEntity(arrival.getEntity());
            arrival.getEntity().getEntityHistory().setServiceArrivalClock(this.clock);
            fel.addEvent(new Departure(0, this.clock + serviceDuration.generateTime(randomizer), arrival.getEntity(), this.serviceDuration, this.timeBetweenArrival));
        }
        
        departure.getEntity().getEntityHistory().addDeparture(departure);
        server.getStatistics().incrementDepartureInstances();

        // Retorno el servidor utilizado para mostrar las estadísticas
        return server;
    }
    
}
