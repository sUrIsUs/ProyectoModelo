package com.engine.distribution;

import java.util.ArrayList;
import java.util.List;

import com.engine.Randomizer;

public class DistributionList implements Distribution {
    
    private List<Distribution> distributionList;

    public DistributionList(){
        distributionList = new ArrayList<>();
    }

    public void addDistribution(Distribution distribution){
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
