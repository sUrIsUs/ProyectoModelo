package com.aeropuerto.scenario.stats;

import com.engine.entity.EntityHistory;
import com.engine.events.Event;
import com.engine.server.Server;
import com.engine.statistics.Statistics;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
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
        this.getWait().aculuteWait(entityHistory.getServiceArrivalClock() - arrival.getClock());
        server.setLastDeparture(departure.getClock());
    }

    @Override
    public void processGeneralStatistics(Server server) {
        System.out.println("Tiempos de transito");
        System.out.println("\tMedio: "+ (this.getTransit().getTotalTransit() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getTransit().getMaxTransit());
        System.out.println("\tMin: " + ((this.getTransit().getMinTransit() == 100000000) ? "": this.getTransit().getMinTransit()));
        System.out.println("Tiempos de espera");
        System.out.println("\tMedio: "+ (this.getWait().getTotalWait() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getWait().getMaxWait());
        System.out.println("\tMin: " + ((this.getWait().getMinWait() == 100000000) ? "": this.getWait().getMinWait()));
        System.out.println("Tiempos de ocio");
        System.out.println("\tMedio: "+ (this.getIdle().getTotalIdle() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getIdle().getMaxIdle());
        System.out.println("\tMin: " + ((this.getIdle().getMinIdle() == 100000000) ? "": this.getIdle().getMinIdle()));
        System.out.println("Tamaños de cola");
        System.out.println("\tMax cola: " + server.getQueue().getMaxSize());
        System.out.println("\tMin cola: " + ((server.getQueue().getMinSize() == 100000000) ? "": server.getQueue().getMinSize()));
    }
   

}
