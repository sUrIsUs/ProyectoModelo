package com.engine.distribution;

import java.util.ArrayList;
import java.util.List;

public class DistributionList {
    List<Distribution> distributionList;

    public DistributionList(){
        this.distributionList = new ArrayList<>();
    }

    public void addDistribution(Distribution distribution){
        this.distributionList.add(distribution);
    }

    public Distribution getDistributionByIndex(int i){
        Distribution distribution = null;
        try{
            distribution = distributionList.get(i); 
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return distribution;
    }
}
