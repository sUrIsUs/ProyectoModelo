package com.aeropuerto.scenario;

import com.bootstrapping.comparators.ServerPrioritizer;
import com.bootstrapping.server.Server;

/**
 * @author Paez Juan Cruz
 * @author Facundo Nicolas Farias Lozano
 */
public class RunwayPrioritizer extends ServerPrioritizer{

    public RunwayPrioritizer(){}

    @Override
    public int compare(Server s1, Server s2) {
        if(s1.isBusy()){
            return 1;
        }
        else if(s2.isBusy()){
            return -1;
        }
        else if(s1.getQueue().size() > s2.getQueue().size()){
            return 1;
        }
        else if(s1.getQueue().size() < s2.getQueue().size()){
            return -1;
        }
        else if(s1.getId() > s2.getId()){
            return 1;
        }
        else{
            return -1;
        }
    }

}
