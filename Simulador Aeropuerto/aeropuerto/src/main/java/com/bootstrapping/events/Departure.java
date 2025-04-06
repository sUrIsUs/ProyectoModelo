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
    public void planificate(FEL fel, Servers servers, Event departure, Randomizer randomizer) {
        Server server = servers.getServer();
        Arrival arrival = null;
        server.getStatistics().setDepartureClock(departure.getClock());
        // Los servers estan okupas
        if(server.getQueue().size() == 0){  
             server.setEntity(null);

            }
            else{
                arrival = (Arrival)server.getQueue().pop();
                fel.addEvent(new Departure(0, serviceDuration.generateTime(randomizer), arrival.getEntity()));
                server.getStatistics().incrementArrivalInstances();
            }
            // Planifico nuevo arribo
            fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft()));
            // Estadisticas
        server.getStatistics().incrementDepartureInstances();
        server.getStatistics().getTransit().determineTransit(server.getStatistics().getDepartureClock() - server.getStatistics().getArrivalClock()); // Completar argumento!!
        server.getStatistics().getIdle().determineIdle( - server.getLastDeparture()); // Completar argumento!!1!
        server.getStatistics().getWait().determineWait(clock);
        //muestro estadisticas
        if(arrival != null){

            server.getStatistics().setArrivalClock(arrival.getClock());
            server.getStatistics().setServiceClock(departure.getClock() - arrival.getClock());
        
        }}
    
}
