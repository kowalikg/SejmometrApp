package com.company;

import java.io.IOException;
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
    public float getMemberLittleCosts() throws IOException{
        float sum = 0;
        int i = 1;
        for (Member m: members) {
            System.out.println("Przetwarzanie posła: " + i + " z " + members.size());
            m.generateCosts();
            sum += m.getLittleCostsValue(cadency);
            i++;
        }
        return sum;
    }
    public float generateAverageCosts() throws IOException{
        float costSum = 0;
        int i = 1;
        for (Member m: members) {
            System.out.println("Przetwarzanie posła: " + i + " z " + members.size());
            m.generateCosts();
            m.generateJourneys();
            costSum += m.getAllCosts(cadency);
            i++;
        }
        return costSum/members.size();
    }

    public ArrayList getMembersItalyVoyages() throws IOException {
        ArrayList<Member> italyMembers = new ArrayList<>();
        int i = 1;
        for (Member m: members) {
            System.out.println("Przetwarzanie posła: " + i + " z " + members.size());
            m.generateJourneys();
            if (m.ifHasVisitedItaly(cadency))
                italyMembers.add(m);
            i++;
        }
        return italyMembers;
    }

    public Member getMemberWithMostJourneys() throws IOException {
        Member winner = members.get(0);
        int i = 1;
        for (Member m: members) {
            System.out.println("Przetwarzanie posła: " + i + " z " + members.size());
            m.generateJourneys();
            if(m.getNumberOfVoyages(cadency) > winner.getNumberOfVoyages(cadency)){
                winner = m;
            }
            i++;
        }
        if(winner.getNumberOfVoyages(cadency) > 0)
            return winner;
        return null;
    }

    public Member getMemberWithLongestJourneys() throws IOException {
        Member winner = members.get(0);
        int i = 1;
        for (Member m: members) {
            System.out.println("Przetwarzanie posła: " + i + " z " + members.size());
            m.generateJourneys();
            if (m.getAllVoyagesTime(cadency) > winner.getAllVoyagesTime(cadency)){
                winner = m;
            }
            i++;
        }
        if (winner.getAllVoyagesTime(cadency) > 0)
            return winner;
        return null;
    }

    public Member getMemberWithMostExpensiveJourney() throws IOException {
        Member winner = members.get(0);
        int i = 1;
        for (Member m: members) {
            System.out.println("Przetwarzanie posła: " + i + " z " + members.size());
            m.generateJourneys();
            if (m.getJourneyMaxCost(cadency) > winner.getJourneyMaxCost(cadency)){
                winner = m;
            }
            i++;
        }
        if (winner.getJourneyMaxCost(cadency) > 0)
            return winner;
        return null;
    }
}
