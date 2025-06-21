package com.engine.events;

import com.engine.distribution.DistributionList;
import com.engine.entity.EntityFactory;

public class ArrivalFactory implements EventFactory{

    @Override
    public Event create(EntityFactory entityFactory, DistributionList distributionList) {
       return new Arrival(2,0,entityFactory.create(), distributionList);
    }
    
}
