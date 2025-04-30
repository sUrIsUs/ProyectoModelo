package com.engine.events;
import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import com.engine.entity.Entity;
import com.engine.entity.EntityFactory;
import com.engine.server.Server;
import com.engine.server.Servers;

public class Departure extends Event {

    Distribution serviceDuration;

    public Departure(int codeDeparture, double clock, Entity entity, Distribution serviceDuration) {
        super(codeDeparture, clock, entity);
        this.serviceDuration = serviceDuration;
    }

    @Override
    public void planificate(FEL fel, Servers servers, Event departure, Randomizer randomizer, EntityFactory entityFactory) {
        Server server = servers.getServerId(this.entity.getServerId());
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            server.setEntity(arrival.getEntity());
            arrival.getEntity().getEntityHistory().setServiceArrivalClock(this.clock);
            fel.addEvent(new Departure(0, this.clock + this.serviceDuration.generateTime(randomizer), arrival.getEntity(), this.serviceDuration));
        }
        
        departure.getEntity().getEntityHistory().addDeparture(departure);
        server.getStatistics().incrementDepartureInstances();
        server.getStatistics().computeStatistics(departure.getEntity().getEntityHistory(), server);
        // Retorno el servidor utilizado para mostrar las estadísticas
    }
    
}
