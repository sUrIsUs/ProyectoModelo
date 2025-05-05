package com.engine.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.engine.comparators.ServerPrioritizer;
import com.engine.distribution.Distribution;
import com.engine.statistics.StatisticsFactory;

public class Servers {
    private List<Server> servers;
    private ServerCodeGenerator serverCodeGenerator;
    private ServerPrioritizer serverPrioritizer;

    public Servers(ServerPrioritizer serverPrioritizer) {
        this.servers = new ArrayList<>();
        this.serverPrioritizer = serverPrioritizer;
        serverCodeGenerator = new ServerCodeGenerator();
    }

    public Server getServer(){
        this.servers.sort(this.serverPrioritizer);
        return this.servers.get(0); 
    }

    public Server getServerId(int id) {
        int i = 0;
        while(servers.get(i).getId() != id){
            i++;
        }
        return servers.get(i);
    }

    public void addServers(int serversCantity, StatisticsFactory statisticsFactory, Distribution distribution, double durability){
        for(int i = 0; i < serversCantity; i++){
            this.servers.add(new Server(serverCodeGenerator, statisticsFactory.create(), distribution, durability));
           
        }
    }

    public void computeServerStatistics(){
        int arrivals = 0, departure = 0;
        double waitMidle = 0;
        System.out.println("===================================================");
        this.servers.sort(Comparator.comparing(Server::getId));
        for(Server server : this.servers){
            System.out.println("Server id: " + server.getId());
            server.getStatistics().processGeneralStatistics(server);
            arrivals += server.getStatistics().getArrivalInstances();
            departure += server.getStatistics().getDepartureInstances();
            System.out.println("Durabilidad final: " + server.getDurability());
            System.out.println("Arrival instances: " + server.getStatistics().getArrivalInstances());
            System.out.println("Departures instances: " + server.getStatistics().getDepartureInstances());
            System.out.println("===================================================");
            // waitMidle += server.getStatistics().getWait().getMinWait;
        }
        // System.out.println("Espera total media: " + );
        System.out.println("Cantidad de entidades que han arribado: " + arrivals);
        System.out.println("Cantidad de entidades que han sido atendidas: " + departure);
    }

}
