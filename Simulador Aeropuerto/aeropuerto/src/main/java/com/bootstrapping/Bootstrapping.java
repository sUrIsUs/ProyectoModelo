package com.bootstrapping;

import com.bootstrapping.events.Arrival;
import com.bootstrapping.events.Event;
import com.bootstrapping.exceptions.NegativeNumberException;
import com.aeropuerto.scenario.airportEntity.Aircraft;
import com.bootstrapping.comparators.EventPrioritizer;
import com.bootstrapping.comparators.ServerPrioritizer;
import com.bootstrapping.distribution.ServiceDuration;
import com.bootstrapping.distribution.TimeBetweenArrival;
import com.bootstrapping.entity.EntityFactory;
import com.bootstrapping.server.Servers;
import com.bootstrapping.statistics.StatisticsFactory;

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
     * @param simulationLength double que indica la duaración de la simulación
     * @param randomizer instancia de Randomizer. Es un generador de números random que se utilizara para generar distribuciones
     * @param serversQuantity entero que indica la cantidad de servidores, los cuales tendrán cada uno su propia fila de eventos
     * @param statistics instancia de Statistics
     * @param serverPrioritizer intancia de ServerPrioriter
     * @param serviceDuration instancia de ServiceDuration
     * @param timeBetweenArrival instancia de TimeBetweenArrival
     */
    public void startSimulation(double simulationLength, Randomizer randomizer, int serversQuantity, StatisticsFactory statisticsFactory, ServerPrioritizer serverPrioritizer, ServiceDuration serviceDuration, TimeBetweenArrival timeBetweenArrival, EntityFactory entityFactory){
        try{
            // Validaciones de parámetros
            if (randomizer == null)
            throw new IllegalArgumentException("El generador de números aleatorios (Randomizer) no puede ser nulo.");
            if (statisticsFactory == null)
                throw new IllegalArgumentException("La instancia de estadísticas no puede ser nula.");
            if (serverPrioritizer == null)
                throw new IllegalArgumentException("El priorizador de servidores no puede ser nulo.");
            if (serviceDuration == null)
                throw new IllegalArgumentException("La duración del servicio no puede ser nula.");
            if (timeBetweenArrival == null)
                throw new IllegalArgumentException("El tiempo entre arribos no puede ser nulo.");
            if (simulationLength <= 0)
                throw new NegativeNumberException("La duración de la simulación debe ser mayor que cero.");
            if (serversQuantity <= 0)
                throw new NegativeNumberException("La cantidad de servidores debe ser mayor que cero.");

            // Inicializar servers
            this.servers = new Servers(serverPrioritizer);
            servers.addServers(serversQuantity, statisticsFactory);;

            //añado primer evento
            fel.addEvent(new Arrival(2,  0, new Aircraft(), serviceDuration, timeBetweenArrival));

            // Empiezo simulacion
            while(simulationLength >= this.clock){
                Event inminent = fel.inminent();
                inminent.planificate(fel, servers, inminent, randomizer, entityFactory);

                //Actualizo clock de la simulación
                this.clock = inminent.getClock();
            }

            //Muestro estadisticas
            servers.computeServerStatistics();
        
        } catch (NegativeNumberException | IllegalArgumentException e) {
            System.out.println("Error al iniciar la simulación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace(); // Opcional: para depurar
        }
    }
}
