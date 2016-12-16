package com.company;

import java.util.ArrayList;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Poseł {
    private String firstName;
    private String lastName;
    private ArrayList<Integer> cadency = new ArrayList<Integer>();

    private ArrayList<Cost> costs = new ArrayList<>();

    public Poseł(/* dane wyciagniete z jsona*/){
        /*
        stwórz posła
        określ kadencję
        pokaż listę wydatków

         */
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
}
