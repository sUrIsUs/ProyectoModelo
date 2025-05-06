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
        this.getTransit().acumulate(departure.getClock() - arrival.getClock());
        this.getIdle().acumulate(arrival.getClock() - server.getLastDeparture());
        this.getWait().acumulate(entityHistory.getServiceArrivalClock() - arrival.getClock());
        server.setLastDeparture(departure.getClock());
    }

    @Override
    public void processGeneralStatistics(Server server) {
        System.out.println("Tiempos de transito de la entidad en el servidor");
        System.out.println("\tMedio: "+ (this.getTransit().getTotal() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getTransit().getMax());
        System.out.println("\tMin: " + ((this.getTransit().getMin() == 100000000) ? "": this.getTransit().getMin()));
        System.out.println("Tiempos de espera de la entidad en el servidor");
        System.out.println("\tMedio: "+ (this.getWait().getTotal() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getWait().getMax());
        System.out.println("\tMin: " + ((this.getWait().getMin() == 100000000) ? "": this.getWait().getMin()));
        System.out.println("Tiempos de ocio del servidor");
        System.out.println("\tMedio: "+ (this.getIdle().getTotal() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getIdle().getMax());
        System.out.println("\tMin: " + ((this.getIdle().getMin() == 100000000) ? "": this.getIdle().getMin()));
        System.out.println("Tamaños de cola del servidor");
        System.out.println("\tMax cola: " + server.getQueue().getMaxSize());
        System.out.println("\tMin cola: " + ((server.getQueue().getMinSize() == 100000000) ? "": server.getQueue().getMinSize()));
    }
   

}
