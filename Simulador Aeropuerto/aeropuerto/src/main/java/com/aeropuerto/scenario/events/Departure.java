package com.aeropuerto.scenario.events;
import com.aeropuerto.Distribution.TimeBetweenArrival;
import com.aeropuerto.scenario.Aircraft;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.aeropuerto.scenario.stats.ArrivalInstances;
import com.aeropuerto.scenario.stats.DepartureInstances;
import com.bootstrapping.Entity;
import com.bootstrapping.Event;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.Server;
import com.bootstrapping.Servers;
import com.bootstrapping.Statistics;

public class Departure extends Event {

    private TimeBetweenArrival timeBetweenArrival;
    public Departure(int type, double clock, Entity entity) {
        super(type, clock, entity);
        timeBetweenArrival = new TimeBetweenArrival();
    }

    @Override
    public void planificate(FEL fel, Servers servers, Event event, Randomizer randomizer, Statistics airportStatistics) {
        Server server = servers.getServer();
        //los servers estan okupas
        if(server.getQueue().size() == 0){  
             server.setEntity(null);
        }
        else{
            Arrival arrival = (Arrival)server.getQueue().pop();
            fel.addEvent(new Departure(0, event.getClock(), arrival.getEntity()));
            //arribo + 1
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft()));
        //salida + 1
        //estadisticas
    }
    
}
