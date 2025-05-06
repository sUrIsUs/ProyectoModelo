package com.engine.statistics;

public abstract class DataCollector {
    
    private double total = 0; 
    private double min = 100000000;
    private double max = 0;
    private double temporal;
    
    public DataCollector() {
        this.temporal = 0;
    }

    public void acumulate(double data){
        this.temporal = (data > 0)? data : 0;
        this.total += this.temporal;
        if(this.temporal > max){
            this.max = this.temporal; 
        }
        if(this.temporal != 0 && this.temporal < min){
            this.min = this.temporal;
        }
    }
    
    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public double getMin() {
        return this.min;
    }

    public void setMin(double min){
        this.min = min;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max){
        this.max = max;
    }

}
