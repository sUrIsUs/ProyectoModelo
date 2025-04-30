package com.engine.server;

import java.util.ArrayList;
import java.util.List;

import com.engine.comparators.ServerPrioritizer;
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

    public void addServers(int serversCantity, StatisticsFactory statisticsFactory){
        for(int i = 0; i < serversCantity; i++){
            this.servers.add(new Server(serverCodeGenerator, statisticsFactory.create()));
           
        }
    }

    public void computeServerStatistics(){
        System.out.println("===================================================");
        for(Server server : this.servers){
            System.out.println("Server id: " + server.getId());
            server.getStatistics().processGeneralStatistics(server);
            System.out.println("===================================================");
        }
        this.getServerId(1).getStatistics().processEventInstances();
    }

}
