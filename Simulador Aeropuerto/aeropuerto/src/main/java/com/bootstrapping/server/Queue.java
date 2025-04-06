package com.bootstrapping.server;
import java.util.ArrayList;
import java.util.List;

import com.bootstrapping.events.Event;

public final class Queue {

    List<Event> queue;
    
    public Queue(){
        this.queue = new ArrayList<>();
    }

    public Event pop(){
        return this.queue.remove(0);
    }

    public void add(Event event){
        this.queue.add(event);
    }

    public boolean isEmpty(){
        return this.queue.isEmpty();
    }

    public int size(){
        return this.queue.size();
    }

}
