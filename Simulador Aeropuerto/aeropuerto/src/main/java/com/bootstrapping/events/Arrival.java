package com.bootstrapping.events;


import com.aeropuerto.Distribution.LandingDuration;
import com.aeropuerto.Distribution.TimeBetweenLanding;
import com.aeropuerto.scenario.Aircraft;
import com.aeropuerto.scenario.stats.AirportStatistics;
import com.aeropuerto.scenario.stats.ArrivalInstances;
import com.aeropuerto.scenario.stats.DepartureInstances;
import com.bootstrapping.FEL;
import com.bootstrapping.Randomizer;
import com.bootstrapping.distribution.Distribution;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.distribution.TimeBetweenArrival;
import com.bootstrapping.entity.Entity;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;
import com.bootstrapping.statistics.Statistics;

public class Arrival extends Event {

    private Distribution serverDuration;
    private Distribution timeBetweenArrival;

    public Arrival(int type, double clock, Entity entity) {
        super(type, clock, entity);
        serverDuration = new ServiceDuration();
        timeBetweenArrival = new TimeBetweenArrival();
    }


    @Override
    public Server planificate(FEL fel, Servers servers, Event arrival, Randomizer randomizer) {
        Server server = servers.getServer();
        this.entity.setServerId(server.getId());
        // Si el server esta ocupado, agrego el arribo a la cola
        if(server.isBusy()){
            server.getQueue().add(arrival);
        }
        // Si no esta ocupado, genero su salida
        else{
            arrival.getEntity().getEntityHistory().setArrivalClock(this.clock);
            server.setEntity(arrival.getEntity());
            fel.addEvent(new Departure(0, serverDuration.generateTime(randomizer), arrival.getEntity()));
            server.getStatistics().incrementArrivalInstances();
        }
        // Planifico nuevo arribo
        fel.addEvent(new Arrival(2, timeBetweenArrival.generateTime(randomizer), new Aircraft())); // Evaluar si conviene hacer lo correcto, y pasar new Entity() en vez de new Aircraft()
        // Estadisticas
        // Computar estadísticas con los atributos de entidad
        return server;
    }
    
}
