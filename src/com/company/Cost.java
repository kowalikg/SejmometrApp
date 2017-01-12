package com.company;

/**
 * Created by Gabrysia on 16.12.2016.
 */
abstract public class Cost {
    protected double cost;
    protected int year;
    public double getCost(){ return cost;}
    public int getYear() { return year;}

    public int getYearCadency(){
        if (year >= 2011 && year <= 2015)
            return 7;
        if (year > 2015)
            return 8;
        return 0;
    }
}
