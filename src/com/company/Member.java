package com.company;

import java.util.ArrayList;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Member {
    private String name;
    private String id;
    private ArrayList<Cost> costs;

    public Member(String name, String id){
        this.name = name;
        this.id = id;
    }
    public void generateCosts(){
        Downloader d = new Downloader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id +
                ".json?layers[]=wydatki");
        d.download();
        Parser parser = new Parser(d.getJsonResult());
        parser.parseCosts();
        costs = parser.getCostList();


    }
    public float getAllCosts(){
        float costsSum = 0;
        for (Cost c: costs
             ) {
            costsSum += c.getCost();

        }
        return costsSum;
    }
    public float getVoyageMaxCost(){
        //znajdz najdrozsza wycieczke zagraniczna
        return 0;
    }
    public float getLittleCostsValue(){
        float sum = 0;
        String littleCosts = "Koszty drobnych napraw i remontów lokalu biura poselskiego";
        for (Cost c: costs) {
            if (c instanceof PersonalCost){
                if(((PersonalCost) c).getDescription().equals(littleCosts)){
                    sum += c.getCost();
                }
            }

        }
        return sum;
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
