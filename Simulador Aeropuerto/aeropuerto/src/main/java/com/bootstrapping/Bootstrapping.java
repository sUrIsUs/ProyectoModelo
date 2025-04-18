package com.bootstrapping;

import com.bootstrapping.events.Arrival;
import com.bootstrapping.events.Departure;
import com.bootstrapping.events.Event;
import com.aeropuerto.scenario.Aircraft;
import com.bootstrapping.comparators.EventPrioritizer;
import com.bootstrapping.comparators.ServerPrioritizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.distribution.TimeBetweenArrival;
import com.bootstrapping.server.Server;
import com.bootstrapping.server.Servers;
import com.bootstrapping.statistics.Statistics;

/**
 * 
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */

public class Bootstrapping{

    private FEL fel;
    private Servers servers;
    private double clock;

    public Bootstrapping() {
        this.fel = new FEL(new EventPrioritizer());
        this.clock = 0;
    }


    /**
     * <p> Método para comenzar una simulación estocástica orientada a eventos discretos </p>
     * @param simulationLenght double que indica la duaración de la simulación
     * @param randomizer instancia de Randomizer. Es un generador de números random que se utilizara para generar distribuciones
     * @param serversCantity entero que indica la cantidad de servidores, los cuales tendrán cada uno su propia fila de eventos
     * @param statistics instancia de Statistics
     * @param serverPrioritizer intancia de ServerPrioriter
     * @param serviceDuration instancia de ServiceDuration
     * @param timeBetweenArrival instancia de TimeBetweenArrival
     */
    public void startSimulation(double simulationLenght, Randomizer randomizer, int serversCantity, Statistics statistics, ServerPrioritizer serverPrioritizer, ServiceDuration serviceDuration, TimeBetweenArrival timeBetweenArrival ){
        // Inicializar fel
        this.servers = new Servers(serverPrioritizer);
        servers.addServers(serversCantity, statistics);;
        Server server;

        //añado primer evento
        fel.addEvent(new Arrival(2,  0, new Aircraft(), serviceDuration, timeBetweenArrival));

        // Empiezo simulacion
        while(simulationLenght >= this.clock){
            Event inminent = fel.inminent();
            server = inminent.planificate(fel, servers, inminent, randomizer);
            if(inminent instanceof Departure){
                server.getStatistics().computeStatistics(inminent.getEntity().getEntityHistory(), server);
            }

            //Actualizo clock de la simulación
            this.clock = inminent.getClock();
        }

        //Muestro estadisticas
        statistics.computeGeneralStatistics(this.servers);
    }

}
