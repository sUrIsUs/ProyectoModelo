package com.aeropuerto.scenario.stats;

import com.engine.entity.EntityHistory;
import com.engine.events.Event;
import com.engine.server.Server;
import com.engine.statistics.Statistics;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
public class AirportStatistics extends Statistics {

    public AirportStatistics(){
        super();
    }

    @Override
    public void computeStatistics(EntityHistory entityHistory, Server server) {
        Event arrival, departure;
        arrival = entityHistory.getArrival();
        departure = entityHistory.getDeparture();
        this.getTransitTotal().acumulate(departure.getClock() - arrival.getClock());
        this.getIdleTotal().acumulate(arrival.getClock() - server.getLastDeparture());
        this.getWaitTotal().acumulate(entityHistory.getServiceArrivalClock() - arrival.getClock());
        server.setLastDeparture(departure.getClock());
    }

    @Override
    public void processGeneralStatistics() {
        System.out.println("===================================================");
        System.out.println("Estadisticas generales");
        System.out.println("Transito medio: "+ this.getTransitTotal().getTotal() / this.getDepartureInstances());
        System.out.println("Transito Máximo: " + this.getTransitTotal().getMax());
        System.out.println("Transito Mínimo: " + this.getTransitTotal().getMin());
        System.out.println();

        System.out.println("Espera medio: "+ this.getWaitTotal().getTotal() / this.getDepartureInstances());
        System.out.println("Espera Máximo: " + this.getWaitTotal().getMax());
        System.out.println("Espera Mínimo: " + this.getWaitTotal().getMin());
        System.out.println();
        
        System.out.println("Ocio total proporcional al tiempo: %"+ (this.getIdleTotal().getTotal() / Statistics.getSimulationLength()) * 100);
        System.out.println("Ocio Máximo: " + this.getIdleTotal().getMax());
        System.out.println("Ocio Mínimo: " + this.getIdleTotal().getMin());
        System.out.println();
        
        System.out.println("Cantidad de entidades que han arribado: " + this.getArrivalInstances());
        System.out.println("Cantidad de entidades que han sido atendidas: " + this.getDepartureInstances());
    }
   

}
