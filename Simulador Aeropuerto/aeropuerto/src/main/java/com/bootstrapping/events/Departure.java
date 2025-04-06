package com.bootstrapping.events;
import com.aeropuerto.Distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.Aircraft;
import com.bootstrapping.Entity;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;

public class Departure extends Event {

    private ServiceDuration serviceDuration;
    private TimeBetweenLanding timeBetweenArrival;
    public Departure(int type, double clock, Entity entity) {
        super(type, clock, entity);
        this.serviceDuration = new ServiceDuration();
        this.timeBetweenArrival = new TimeBetweenLanding();
    }

    @Override
    public void planificate(FEL fel, Servers servers, Event event, Randomizer randomizer) {
        Server server = servers.getServer();
        Departure departure;
        Arrival arrival;
        // Los servers estan okupas
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
            }
            else{
                arrival = (Arrival)server.getQueue().pop();
                server.getStatistics().getWait().determineWait(event.getClock() - arrival.getClock());
                departure = new Departure(0, serviceDuration.generateTime(randomizer), arrival.getEntity())
                fel.addEvent(departure);
                server.getStatistics().incrementArrivalInstances();
            }
            // Planifico nuevo arribo
            fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft()));
            // Estadisticas
        server.getStatistics().getTransit().determineTransit(event.getClock() - ( + server.getStatistics().getWait().getTemporalWait()));
        server.getStatistics().incrementDepartureInstances();
        server.getStatistics().getTransit().determineTransit(0); // Completar argumento!!1!
        //Mostrar estadisticas
        server.getStatistics().getTransit().determineTransit(departure.getClock() - arrival.getClock());
    }
    
}
