package com.engine.bootstrapping;

import com.aeropuerto.scenario.airportEntity.Aircraft;
import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.comparators.EventPrioritizer;
import com.engine.comparators.ServerPrioritizer;
import com.engine.distribution.Distribution;
import com.engine.entity.EntityFactory;
import com.engine.events.Arrival;
import com.engine.events.Event;
import com.engine.exceptions.NegativeNumberException;
import com.engine.server.Servers;
import com.engine.statistics.Statistics;

/**
 * 
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */

public class Bootstrapping{

    private FEL fel;
    private Servers servers;
    private Clock clock;
    private Statistics statistics;

    public Bootstrapping(Statistics statistics) {
        this.fel = new FEL(new EventPrioritizer());
        this.clock = new Clock(0);
        this.statistics = statistics;
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
     * @throws Exception 
     */
    public Statistics startSimulation(
    double simulationLength,
    Randomizer randomizer,
    int serversQuantity,
    double durability,
    Distribution serverDurability,
    ServerPrioritizer serverPrioritizer,
    Distribution serviceDuration,
    Distribution timeBetweenArrival,
    EntityFactory entityFactory
    ) throws Exception {
    try {
        validateParameters(simulationLength, randomizer, serversQuantity, durability,
                           serverDurability, serverPrioritizer, serviceDuration,
                           timeBetweenArrival, entityFactory);

        // Inicializar servidores
        this.servers = new Servers(serverPrioritizer);
        servers.addServers(serversQuantity, serverDurability, durability);

        // Configurar duración de simulación
        Statistics.setSimulationLength(simulationLength);

        // Añadir primer evento
        fel.addEvent(new Arrival(2, 0, new Aircraft(), serviceDuration, timeBetweenArrival));

        // Comenzar simulación
        while (simulationLength >= Clock.clock) {
            Event inminent = fel.inminent();
            this.clock.setClock(inminent.getClock());
            inminent.planificate(fel, servers, inminent, randomizer, entityFactory, this.statistics);
        }

        this.statistics.setServers(servers);
        // this.statistics.processGeneralStatistics();

    }catch (NegativeNumberException  e) {
        throw new NegativeNumberException("Error al iniciar la simulación: " + e.getMessage());
    }catch (IllegalArgumentException e) {
        System.out.println();
        throw new IllegalArgumentException("Error al iniciar la simulación: " + e.getMessage());
    }catch (Exception e) {
        e.printStackTrace();
         throw new Exception("Ha ocurrido un error inesperado: " + e.getMessage());
    }
    
    return this.statistics;
}

private void validateParameters(
    double simulationLength,
    Randomizer randomizer,
    int serversQuantity,
    double durability,
    Distribution serverDurability,
    ServerPrioritizer serverPrioritizer,
    Distribution serviceDuration,
    Distribution timeBetweenArrival,
    EntityFactory entityFactory
) throws NegativeNumberException {
    if (simulationLength <= 0)
        throw new NegativeNumberException("La duración de la simulación debe ser mayor que cero.");

    if (serversQuantity <= 0)
        throw new NegativeNumberException("La cantidad de servidores debe ser mayor que cero.");

    if (durability <= 0)
        throw new NegativeNumberException("La durabilidad debe ser mayor que cero.");

    if (randomizer == null)
        throw new IllegalArgumentException("El generador de números aleatorios (Randomizer) no puede ser nulo.");

    if (serverDurability == null)
        throw new IllegalArgumentException("La distribución de durabilidad de servidores no puede ser nula.");

    if (serverPrioritizer == null)
        throw new IllegalArgumentException("El priorizador de servidores no puede ser nulo.");

    if (serviceDuration == null)
        throw new IllegalArgumentException("La duración del servicio no puede ser nula.");

    if (timeBetweenArrival == null)
        throw new IllegalArgumentException("El tiempo entre arribos no puede ser nulo.");

    if (entityFactory == null)
        throw new IllegalArgumentException("El generador de entidades no puede ser nulo.");
}

}
