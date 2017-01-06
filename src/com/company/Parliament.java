package com.company;

import java.util.ArrayList;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class Parliament {
    private int cadency;
    private ArrayList<Member> members = new ArrayList<>();

    public Parliament(int cadency){
        this.cadency = cadency;
    }
    public void pushMembers(ArrayList m){
        members.addAll(m);

    }
    public void show(){
        for (Member m: members){
            System.out.println(m);
        }
    }
    public void generateAverageCosts(){
        for (Member m: members
             ) {
            m.generateCosts();

        }
    }
}
