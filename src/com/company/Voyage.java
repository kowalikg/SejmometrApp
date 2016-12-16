package com.company;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Voyage extends Cost{
    private int time;
    private String country;

    public Voyage(/*argumenty z JSONA*/){};

    public String getCountry(){return country;}
    public int getTime(){return time;}

}