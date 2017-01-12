package com.company;

import java.io.IOException;
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
    public void generateCosts() throws IOException{
        Downloader d = new Downloader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id +
                ".json?layers[]=wydatki");
        d.download();
        Parser parser = new Parser(d.getJsonResult());
        parser.generatePersonalCosts();
        costs.addAll(parser.getPersonalCostList());
    }
    public void generateJourneys() throws IOException{
        Downloader d = new Downloader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id +
                ".json?layers[]=wyjazdy");
        d.download();
        Parser parser = new Parser(d.getJsonResult());
        parser.generateJourneys();
        costs.addAll(parser.getJourneys());
    }

    public float getAllCosts(int cadency){
        float costsSum = 0;
        for (Cost c: costs
             ) {
            if(c.getYearCadency() == cadency){
                costsSum += c.getCost();
            }


        }
        return costsSum;
    }
    public double getJourneyMaxCost(int cadency){
        double maxCost = 0;
        for (Cost c: costs) {
            if (c instanceof Journey){
                if(c.getYearCadency() == cadency) {
                    if (maxCost < c.getCost()) {
                        maxCost = c.getCost();
                    }
                }
            }
        }
        return maxCost;
    }
    public float getLittleCostsValue(int cadency){
        float sum = 0;
        String littleCosts = "Koszty drobnych napraw i remontów lokalu biura poselskiego";
        for (Cost c: costs) {
            if (c instanceof PersonalCost){
                if(c.getYearCadency() == cadency) {
                    if (((PersonalCost) c).getDescription().equals(littleCosts)) {
                        sum += c.getCost();
                    }
                }
            }

        }
        return sum;
    }
    public int getNumberOfVoyages(int cadency){
        int numberOfJourneys = 0;

        for (Cost c: costs) {
            if (c instanceof Journey){
                if(c.getYearCadency() == cadency) {
                    numberOfJourneys++;
                }
            }
        }
        return numberOfJourneys;
    }
    public int getAllVoyagesTime(int cadency){
        int daysAbroad = 0;

        for (Cost c: costs) {
            if (c instanceof Journey){
                if(c.getYearCadency() == cadency) {
                    daysAbroad += ((Journey) c).getTime();
                }
            }
        }
        return daysAbroad;
    }
    public String toString(){
        return "Numer : " + id +  ", imię i nazwisko : " + name;
    }

    public boolean ifHasVisitedItaly(int cadency) {
        if (costs != null){
            for (Cost c: costs ) {
                if (c instanceof Journey){
                    if(c.getYearCadency() == cadency) {
                        if (((Journey) c).getCountry().equals("Włochy"))
                            return true;
                    }
                }
            }
        }
        return false;
    }


}
