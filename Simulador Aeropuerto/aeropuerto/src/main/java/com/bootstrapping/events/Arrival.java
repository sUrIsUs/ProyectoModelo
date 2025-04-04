package com.bootstrapping.events;


import com.aeropuerto.Distribution.LandingDuration;
import com.aeropuerto.Distribution.TimeBetweenArrival;
import com.aeropuerto.scenario.Aircraft;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.aeropuerto.scenario.stats.ArrivalInstances;
import com.aeropuerto.scenario.stats.DepartureInstances;
import com.bootstrapping.Distribution;
import com.bootstrapping.Entity;
import com.bootstrapping.Event;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.Server;
import com.bootstrapping.Servers;
import com.bootstrapping.statistics.Statistics;

public class Arrival extends Event {

    private Distribution serverDuration;
    private Distribution timeBetweenArrival;

    public Arrival(int type, double clock, Entity entity) {
        super(type, clock, entity);
        serverDuration = new LandingDuration();
        timeBetweenArrival = new TimeBetweenArrival();
    }


    @Override
    public void planificate(FEL fel, Servers servers, Event arrival, Randomizer randomizer, Statistics statistics) {
        Server server = servers.getServer();
        // Si el server esta ocupado, agrego el arribo a la cola
        if(server.isBusy()){
            server.getQueue().add(arrival);
        }
        // Si no esta ocupado, genero su salida
        else{
            server.setEntity(arrival.getEntity());
            fel.addEvent(new Departure(0, serverDuration.generateTime(randomizer), arrival.getEntity()));
            //arribo + 1
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft()));
        // Estadisticas
        statistics.computeIdle();

    }
    
}
