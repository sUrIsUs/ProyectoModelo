package com.aeropuerto.scenario.stats;

import com.bootstrapping.entity.EntityHistory;
import com.bootstrapping.events.Event;
import com.bootstrapping.server.Server;
import com.bootstrapping.statistics.Statistics;

public class AirportStatistics extends Statistics{

    public AirportStatistics(){
        super();
    }

    @Override
    public void computeStatistics(EntityHistory entityHistory, Server server) {
        Event arrival, departure;
        arrival = entityHistory.getArrival();
        departure = entityHistory.getDeparture();
        this.getTransit().acumulateTransit(departure.getClock() - arrival.getClock());
        this.getIdle().acumulateIdle(arrival.getClock() - server.getLastDeparture());
        this.getWait().aculuteWait(entityHistory.getArrivalClock() - arrival.getClock());
        server.setLastDeparture(departure.getClock());
        System.out.println();
    }

    @Override
    public void computeGeneralStatistics() {
        System.out.println("Cantidad de aeronaves que han arribado: " + this.getArrivalInstances());
    }
    

}
