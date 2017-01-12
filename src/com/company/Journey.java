package com.company;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Journey extends Cost{
    private int time;
    private String country;

    public Journey(String country, int time, double cost, int year){
        this.country = country;
        this.time = time;
        this.cost = cost;
        this.year = year;
    };

    public String getCountry(){return country;}
    public int getTime(){return time;}

}