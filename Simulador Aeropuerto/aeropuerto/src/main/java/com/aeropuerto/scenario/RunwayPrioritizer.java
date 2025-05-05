package com.aeropuerto.scenario;

import java.util.Comparator;

import com.engine.comparators.ServerPrioritizer;
import com.engine.server.Server;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
public class RunwayPrioritizer extends ServerPrioritizer{

    public RunwayPrioritizer(){}

    @Override
    public int compare(Server s1, Server s2) {
        return Comparator
            .comparing(Server::isBusy)
            .thenComparingInt(s -> s.getQueue().size())
            .thenComparingInt(Server::getId)
            .compare(s1, s2);
    }

}
