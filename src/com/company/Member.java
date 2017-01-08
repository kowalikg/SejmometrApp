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
        Downloader d = new Downloader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id +
                ".json?layers[]=wydatki");
        d.download();
        Parser parser = new Parser(d.getJsonResult());
        parser.generatePersonalCosts();
        costs.addAll(parser.getPersonalCostList());
    }
    public void generateJourneys(){
        Downloader d = new Downloader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id +
                ".json?layers[]=wyjazdy");
        d.download();
        Parser parser = new Parser(d.getJsonResult());
        parser.generateJourneys();
        costs.addAll(parser.getJourneys());
    }

    public float getAllCosts(){
        float costsSum = 0;
        for (Cost c: costs
             ) {
            costsSum += c.getCost();

        }
        return costsSum;
    }
    public double getJourneyMaxCost(){
        double maxCost = 0;
        for (Cost c: costs) {
            if (c instanceof Journey){
                if (maxCost < c.getCost()){
                    maxCost = c.getCost();
                }
            }
        }
        return maxCost;
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
        int numberOfJourneys = 0;

        for (Cost c: costs) {
            if (c instanceof Journey){
                numberOfJourneys++;
            }
        }
        return numberOfJourneys;
    }
    public int getVoyagesTime(){
        int daysAbroad = 0;

        for (Cost c: costs) {
            if (c instanceof Journey){
                daysAbroad += ((Journey) c).getTime();
            }
        }
        return daysAbroad;
    }
    public String toString(){
        return "Numer : " + id +  ", imię i nazwisko : " + name;
    }

    public boolean ifHasVisitedItaly() {
        if (costs != null){
            for (Cost c: costs ) {
                if (c instanceof Journey){
                    if (((Journey) c).getCountry().equals("Włochy"))
                        return true;
                }
            }
        }
        return false;
    }

}
