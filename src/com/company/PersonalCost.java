package com.company;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class PersonalCost extends Cost {
    private String description;
    private int number;
    private int year;

    public PersonalCost(int number, String description, float value, int year){
        this.number = number;
        this.description = description;
        this.cost = value;
        this.year = year;
    }
    public String getDescription(){ return description;}


    public String toString(){
        return "Koszt " + number + "\n" +
                "Opis: " + description + "\n" +
                "Rok: " + year + "\n" +
                "Wartość " + cost;
    }
}
