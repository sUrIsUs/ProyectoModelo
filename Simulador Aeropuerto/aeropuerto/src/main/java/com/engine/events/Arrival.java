package com.engine.events;

import java.util.List;

import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.distribution.Distribution;
import com.engine.distribution.DistributionList;
import com.engine.entity.Entity;
import com.engine.entity.EntityFactory;
import com.engine.server.Server;
import com.engine.server.Servers;
import com.engine.statistics.Statistics;

public class Arrival extends Event {

    public Arrival(int codeArrival, double clock, Entity entity, DistributionList distributionList) {
        super(codeArrival, clock, entity, distributionList);
    }


    @Override
    public void planificate(FEL fel, Servers servers, Event arrival, Randomizer randomizer, EntityFactory entityFactory, Statistics statistics) {
        Server server = servers.getServer();
        this.entity.setServerId(server.getId());
        arrival.getEntity().getEntityHistory().addArrival(arrival);
        // Cuando un avión spawnea en la simu, incremento la cantidad de arribos en uno
        statistics.incrementArrivalInstances();
        // Si el server esta ocupado, agrego el arribo a la cola
        if(server.isBusy()){
            server.getQueue().add(arrival);
        }
        // Si no esta ocupado, genero su salida
        else{
            DistributionList departureDistribution = new DistributionList();
            departureDistribution.addDistribution(this.distributionList.getDistributionByIndex(0));
            double departureClock = this.clock + departureDistribution.getDistributionByIndex(0).generateValue(randomizer);
            arrival.getEntity().getEntityHistory().setServiceArrivalClock(this.clock);
            server.setEntity(arrival.getEntity());
            fel.addEvent(
                new Departure(0, 
                departureClock, 
                arrival.getEntity(), 
                departureDistribution));
            server.setDepartureClock(departureClock);
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, this.clock + this.distributionList.getDistributionByIndex(1).generateValue(randomizer), entityFactory.create(), this.distributionList));
    }
    
}
