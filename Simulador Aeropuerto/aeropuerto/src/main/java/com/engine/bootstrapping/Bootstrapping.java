package com.engine.bootstrapping;

import com.engine.FEL;
import com.engine.Randomizer;
import com.engine.comparators.EventPrioritizer;
import com.engine.comparators.ServerPrioritizer;
import com.engine.distribution.Distribution;
import com.engine.distribution.DistributionList;
import com.engine.entity.EntityFactory;
import com.engine.events.Event;
import com.engine.events.EventFactory;
import com.engine.exceptions.NegativeNumberException;
import com.engine.server.Servers;
import com.engine.statistics.Statistics;

/**
 * 
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */

public class Bootstrapping{

    private final FEL fel;
    private Servers servers;
    private final Clock clock;
    private final Statistics statistics;

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
    DistributionList distributionList,
    EntityFactory entityFactory,
    EventFactory eventFactory
    ) throws Exception {
    try {
        validateParameters(simulationLength, randomizer, serversQuantity, durability,
                           serverDurability, serverPrioritizer, distributionList, entityFactory);

        // Inicializar servidores
        this.servers = new Servers(serverPrioritizer);
        servers.addServers(serversQuantity, serverDurability, durability);

        // Configurar duración de simulación
        Statistics.setSimulationLength(simulationLength);

        // Añadir primer evento

        fel.addEvent(eventFactory.create(entityFactory,distributionList));

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
    DistributionList distributionList,
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

    if (distributionList == null)
        throw new IllegalArgumentException("La lista de distribuciones del servicio no puede ser nula.");

    if (entityFactory == null)
        throw new IllegalArgumentException("El generador de entidades no puede ser nulo.");
}

}
