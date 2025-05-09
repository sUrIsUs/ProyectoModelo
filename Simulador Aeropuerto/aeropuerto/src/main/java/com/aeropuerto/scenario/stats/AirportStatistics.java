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
        this.getTransit().acumulate(departure.getClock() - arrival.getClock());
        this.getIdle().acumulate(arrival.getClock() - server.getLastDeparture());
        this.getWait().acumulate(entityHistory.getServiceArrivalClock() - arrival.getClock());
        server.setLastDeparture(departure.getClock());
    }

    @Override
    public void processServerStatistics(Server server) {
        System.out.println("Server id: " + server.getId());
        System.out.println("Tiempos de transito de la entidad en el servidor");
        System.out.println("\tMedio: "+ (this.getTransit().getTotal() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getTransit().getMax());
        System.out.println("\tMin: " + ((this.getTransit().getMin() == 100000000) ? "": this.getTransit().getMin()));
        System.out.println("Tiempos de espera de la entidad en el servidor");
        System.out.println("\tMedio: "+ (this.getWait().getTotal() / this.getDepartureInstances()));
        System.out.println("\tMax: " + this.getWait().getMax());
        System.out.println("\tMin: " + ((this.getWait().getMin() == 100000000) ? "": this.getWait().getMin()));
        System.out.println("Tamaños de cola del servidor");
        System.out.println("\tMax cola: " + server.getQueue().getMaxSize());
        System.out.println("\tMin cola: " + ((server.getQueue().getMinSize() == 100000000) ? "": server.getQueue().getMinSize()));
        System.out.println("Durabilidad final: " + server.getDurability());
        System.out.println("Arrival instances: " + server.getStatistics().getArrivalInstances());
        System.out.println("Departures instances: " + server.getStatistics().getDepartureInstances());
        // Estadisticas generales
        // Computo Tránsito total
        Statistics.getTransitTotal().setTotal(this.getTransit().getTotal() + Statistics.getTransitTotal().getTotal()); // Total
        Statistics.getTransitTotal().setMax( // Máximo
            (Statistics.getTransitTotal().getMax() < this.getTransit().getMax()) 
            ? this.getTransit().getMax() 
            : Statistics.getTransitTotal().getMax());
        Statistics.getTransitTotal().setMin( // Mínimo
            (Statistics.getTransitTotal().getMin() > this.getTransit().getMin()) 
            ? this.getTransit().getMin() 
            : Statistics.getTransitTotal().getMin());

        // Computo Espera total
        Statistics.getWaitTotal().setTotal(this.getWait().getTotal() + Statistics.getWaitTotal().getTotal()); // Total
        Statistics.getWaitTotal().setMax( // Máximo
            (Statistics.getWaitTotal().getMax() < this.getWait().getMax()) 
            ? this.getWait().getMax() 
            : Statistics.getWaitTotal().getMax());
        Statistics.getWaitTotal().setMin( // Mínimo
            (Statistics.getWaitTotal().getMin() > this.getWait().getMin()) 
            ? this.getWait().getMin() 
            : Statistics.getWaitTotal().getMin());

        // Computo Ocio total  
        Statistics.getIdleTotal().setTotal(this.getIdle().getTotal() + Statistics.getIdleTotal().getTotal()); // Total
        Statistics.getIdleTotal().setMax( // Máximo
            (Statistics.getIdleTotal().getMax() < this.getIdle().getMax()) 
            ? this.getIdle().getMax() 
            : Statistics.getIdleTotal().getMax());
        Statistics.getIdleTotal().setMin( // Mínimo
            (Statistics.getIdleTotal().getMin() > this.getIdle().getMin()) 
            ? this.getIdle().getMin() 
            : Statistics.getIdleTotal().getMin());
    }

    @Override
    public void processGeneralStatistics(int arrivals, int departures) {
        System.out.println("===================================================");
        System.out.println("Estadisticas generales");
        System.out.println("Transito medio: "+ Statistics.getTransitTotal().getTotal() / departures);
        System.out.println("Transito Máximo: " + Statistics.getTransitTotal().getMax());
        System.out.println("Transito Mínimo: " + Statistics.getTransitTotal().getMin());
        System.out.println();

        System.out.println("Espera medio: "+ Statistics.getWaitTotal().getTotal() / departures);
        System.out.println("Espera Máximo: " + Statistics.getWaitTotal().getMax());
        System.out.println("Espera Mínimo: " + Statistics.getWaitTotal().getMin());
        System.out.println();
        
        System.out.println("Ocio total proporcional al tiempo: %"+ (Statistics.getIdleTotal().getTotal() / Statistics.getSimulationLength()) * 100);
        System.out.println("Ocio Máximo: " + Statistics.getIdleTotal().getMax());
        System.out.println("Ocio Mínimo: " + Statistics.getIdleTotal().getMin());
        System.out.println();
        
        System.out.println("Cantidad de entidades que han arribado: " + arrivals);
        System.out.println("Cantidad de entidades que han sido atendidas: " + departures);
    }
   

}
