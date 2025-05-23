package com.engine.server;
import java.util.ArrayList;
import java.util.List;

import com.engine.events.Event;

public final class Queue {

    private List<Event> queue;
    private int maxSize;
    private int minSize;
    
    public Queue(){
        this.queue = new ArrayList<>();
        this.maxSize = 0;
        this.minSize = 100000000;
    }

    public Event pop(){
        return this.queue.remove(0);
    }

    public void add(Event event){
        this.queue.add(event);
        // Computo máximo de la fila
        if(this.queue.size() > this.maxSize) this.maxSize = this.queue.size();
        // Computo mínimo de la fila
        if(this.queue.size() < this.minSize && this.queue.size() != 0) this.minSize = this.queue.size();
    }

    public boolean isEmpty(){
        return this.queue.isEmpty();
    }

    public int size(){
        return this.queue.size();
    }

    public int getMaxSize(){
        return this.maxSize;
    }

    public int getMinSize(){
        return (this.minSize == 100000000)? 0 : this.minSize;
    }

}
