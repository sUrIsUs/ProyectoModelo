package com.engine.events;
import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import com.engine.entity.Entity;
import com.engine.entity.EntityFactory;
import com.engine.server.Server;
import com.engine.server.Servers;
import com.engine.statistics.Statistics;

public class Departure extends Event {

    Distribution serviceDuration;

    public Departure(int codeDeparture, double clock, Entity entity, Distribution serviceDuration) {
        super(codeDeparture, clock, entity);
        this.serviceDuration = serviceDuration;
    }

    @Override
    public void planificate(FEL fel, Servers servers, Event departure, Randomizer randomizer, EntityFactory entityFactory, Statistics statistics) {
        Server server = servers.getServerId(this.entity.getServerId());
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            double departureClock = this.clock + this.serviceDuration.generateValue(randomizer);
            server.setEntity(arrival.getEntity());
            arrival.getEntity().getEntityHistory().setServiceArrivalClock(this.clock);
            fel.addEvent(new Departure(0, departureClock, arrival.getEntity(), this.serviceDuration));
            server.setDepartureClock(departureClock);
        }
        
        departure.getEntity().getEntityHistory().addDeparture(departure);
        statistics.incrementDepartureInstances();
        statistics.computeStatistics(departure.getEntity().getEntityHistory(), server);
        server.determineDurability(randomizer);
    }
    
}
