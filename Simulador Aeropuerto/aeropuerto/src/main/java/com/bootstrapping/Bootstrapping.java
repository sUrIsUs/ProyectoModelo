package com.bootstrapping;

import java.util.ArrayList;
import java.util.Comparator;

import com.bootstrapping.events.Departure;
import com.bootstrapping.events.Event;
import com.bootstrapping.fel.FEL;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;
import com.bootstrapping.statistics.Statistics;

public class Bootstrapping{

    private FEL fel;
    private Servers servers;
    private double clock;

    public Bootstrapping() {
        this.fel = new FEL();
        this.clock = 0;
    }

    public void startSimulation(double simulationLenght, Randomizer randomizer, int serversCantity, Statistics statistics, Comparator<Server> comparator){
        // Inicializar fel
        this.servers = new Servers(comparator);
        servers.addServers(serversCantity, statistics);;
        Server server;
        // Empiezo simulacion
        while(simulationLenght >= clock){
            Event inminent = fel.inminent();
            server = inminent.planificate(fel, servers, inminent, randomizer);
            if(inminent instanceof Departure){
                server.getStatistics().computeStatistics(inminent.getEntity().getEntityHistory(), server);
            }
        }
    }

}
