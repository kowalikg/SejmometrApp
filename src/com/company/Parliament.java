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
    public float getMemberLittleCosts(){
        float sum = 0;
        for (Member m: members) {
            m.generateCosts();
            sum += m.getLittleCostsValue();
        }
        return sum;
    }
    public float generateAverageCosts(){
        float costSum = 0;
        for (Member m: members) {
            m.generateCosts();
            m.generateJourneys();
            costSum += m.getAllCosts();
        }
        return costSum/members.size();
    }

    public ArrayList getMembersItalyVoyages() {
        ArrayList<Member> italyMembers = new ArrayList<>();
        for (Member m: members) {
            m.generateJourneys();
            if (m.ifHasVisitedItaly())
                italyMembers.add(m);
        }
        return italyMembers;
    }

    public Member getMemberWithMostJourneys() {
        Member winner = members.get(0);
        for (Member m: members) {
            m.generateJourneys();
            if(m.getNumberOfVoyages() > winner.getNumberOfVoyages()){
                winner = m;
            }
        }
        if(winner.getNumberOfVoyages() > 0)
            return winner;
        return null;
    }

    public Member getMemberWithLongestJourneys() {
        Member winner = members.get(0);
        for (Member m: members) {
            m.generateJourneys();
            if (m.getVoyagesTime() > winner.getVoyagesTime()){
                winner = m;
            }
        }
        if (winner.getVoyagesTime() > 0)
            return winner;
        return null;
    }

    public Member getMemberWithMostExpensiveJourney() {
        Member winner = members.get(0);
        for (Member m: members) {
            m.generateJourneys();
            if (m.getJourneyMaxCost() > winner.getJourneyMaxCost()){
                winner = m;
            }
        }
        if (winner.getJourneyMaxCost() > 0)
            return winner;
        return null;
    }
}
