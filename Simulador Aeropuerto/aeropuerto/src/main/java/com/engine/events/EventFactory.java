package com.engine.events;

import com.engine.distribution.DistributionList;
import com.engine.entity.EntityFactory;

public interface  EventFactory {
    public Event create(EntityFactory entityFactory, DistributionList distributionList);
}
