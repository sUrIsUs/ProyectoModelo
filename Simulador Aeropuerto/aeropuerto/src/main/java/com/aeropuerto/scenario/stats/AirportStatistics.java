package com.aeropuerto.scenario.stats;

import com.bootstrapping.entity.EntityHistory;
import com.bootstrapping.events.Event;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;
import com.bootstrapping.statistics.Idle;
import com.bootstrapping.statistics.Statistics;
import com.bootstrapping.statistics.Transit;
import com.bootstrapping.statistics.Wait;

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
        System.out.println();
    }

    @Override
    public void computeGeneralStatistics(Servers servers) {
        System.out.println("Cantidad de aeronaves que han arribado: " + this.getArrivalInstances());
        System.out.println("Cantidad de aeronaves que han aterrizado: " + this.getDepartureInstances());
        System.out.println("Tiempos de transito");
        System.out.println("\tMedio: "+ (Transit.getTotalTransit() / this.getDepartureInstances()));
        System.out.println("\tMax: " + Transit.getMaxTransit());
        System.out.println("\tMin: " + Transit.getMinTransit());
        System.out.println("Tiempos de espera");
        System.out.println("\tMedio: "+ (Wait.getTotalWait() / this.getDepartureInstances()));
        System.out.println("\tMax: " + Wait.getMaxWait());
        System.out.println("\tMin: " + Wait.getMinWait());
        System.out.println("Tiempos de ocio");
        System.out.println("\tMedio: "+ (Idle.getTotalIdle() / this.getDepartureInstances()));
        System.out.println("\tMax: " + Idle.getMaxIdle());
        System.out.println("\tMin: " + Idle.getMinIdle());
        System.out.println("Tamaños de cola");
        System.out.println("\tMax cola: " + servers.getServerId(1).getQueue().getMaxSize());
        System.out.println("\tMin cola: " + servers.getServerId(1).getQueue().getMinSize());
    }
    

}
