package com.company;

import java.util.ArrayList;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Member {
    private String name;
    private String id;
    private ArrayList<Cost> costs = new ArrayList<>();

    public Member(String name, String id){
        this.name = name;
        this.id = id;
    }
    public void generateCosts(){


    }
    public float getAllCosts(){
        //suma wszystkich wydatków z podróżami włącznie
        return 0;
    }
    public float getVoyageMaxCost(){
        //znajdz najdrozsza wycieczke zagraniczna
        return 0;
    }
    public float getLittleThingsValue(){
        //zwróć sumę małych wydatków
        return 0;
    }
    public int getNumberOfVoyages(){
        //zwroc liczbe podrozy zagranicznych
        return 0;
    }
    public int getVoyagesTime(){
        //zwróć łączny czas za granicą
        return 0;
    }
    public String toString(){
        return "Numer : " + id +  ", imię i nazwisko : " + name;
    }
}
