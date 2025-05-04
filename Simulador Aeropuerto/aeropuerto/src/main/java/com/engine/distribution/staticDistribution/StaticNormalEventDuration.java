package com.engine.distribution.staticDistribution;

import com.engine.Randomizer;
import com.engine.distribution.Distribution;

public class StaticNormalEventDuration implements Distribution{
    
    private double muNormal;
    private double sigmaNormal;
    private Distribution distributionRandomVariate; 
    private double muDistribution;
    private double sigmaDistribution;

    public StaticNormalEventDuration( double  muNormal, double sigmaNormal, Distribution distributionRandomVariate, double muDistribution, double sigmaDistribution){
        this.muNormal = muNormal;
        this.sigmaNormal = sigmaNormal;
        this.distributionRandomVariate = distributionRandomVariate;
        this.muDistribution = muDistribution;
        this.sigmaDistribution = sigmaDistribution;
    }

    @Override
    public double generateValue(Randomizer randomizer) {
        
        int n = 50;
        double z = 0, mu_z = muDistribution * n, sigma_z = sigmaDistribution * n, Z;

        for(int i = 0; i < n; i++){
            z += distributionRandomVariate.generateValue(randomizer);
        }
        
        Z = (z - mu_z) / sigma_z;
        System.out.println("Z grande: " + Z);

        return Z * this.sigmaNormal + this.muNormal;

    }


}