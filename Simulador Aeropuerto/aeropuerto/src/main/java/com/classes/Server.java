package com.classes;

public class Server {
    
    private Entity entity;
    private final Queue queue = new Queue();
    
    public Server(Queue queue) {
        this.entity = null;
    }
   
    public boolean isBusy(){
        return entity != null;
    }

    public void setEntity(Entity entity){
        this.entity = entity;
    }
    
    public Queue getQueue() {
        return queue;
    }

}
