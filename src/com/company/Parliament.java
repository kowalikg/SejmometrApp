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
    public void generateCosts(){
        for (Member m: members
                ) {
            m.generateCosts();
        }
    }
    public float getMemberLittleCosts(){
        float sum = 0;
        for (Member m: members) {
            sum += m.getLittleCostsValue();
        }
        return sum;
    }
    public float generateAverageCosts(){
        float costSum = 0;
        for (Member m: members) {
            costSum += m.getAllCosts();
        }
        return costSum/members.size();
    }
}
