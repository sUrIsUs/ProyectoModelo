package com.engine.distribution;

import java.util.ArrayList;
import java.util.List;

import com.engine.Randomizer;

public class DistributionAccumulator implements Distribution {
    
    private List<Distribution> distributionList;

    public DistributionAccumulator(){
        distributionList = new ArrayList<>();
    }

    public void accumulateDistribution(Distribution distribution){
        distributionList.add(distribution);
    }

    @Override
    public double generateValue(Randomizer randomizer) {
        double value = 0;
        for(Distribution distribution : distributionList){
            value += distribution.generateValue(randomizer);
        }    
        return value;
    }

}
