package com.bootstrapping;

import java.util.ArrayList;
import java.util.List;

public class FEL {
    private List<Event> fel;

    public FEL(){
        fel = new ArrayList<>();
    }

    public void addEvent(Event event){
        fel.add(event);
    }

    public Event inminent(){
        return fel.removeFirst();
    }
} 