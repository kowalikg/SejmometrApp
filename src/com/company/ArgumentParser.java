package com.company;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Gabrysia on 16.12.2016.
 */
public class ArgumentParser {

    HashMap<String, Option> optionHashMap = new HashMap<>();
    private String[] argsToValidate;

    private Option option;
    private int cadency;
    private String memberFirstName;
    private String memberLastName;

    public ArgumentParser(String[] args){
        this.argsToValidate = Arrays.copyOf(args, args.length);
    }

    public void validateArgs() throws IllegalArgumentException{

        if (argsToValidate.length != 2 && argsToValidate.length != 4)
            throw new IllegalArgumentException("Wrong number of arguments: should be 2 or 4");

        if(!validateCadency())
            throw new IllegalArgumentException("Wrong arguments: cadency.");

        generateOptionHashMap();

        if(argsToValidate.length == 4){
            if(!validateMemberName())
                throw new IllegalArgumentException("Wrong arguments: wrong option to member.");
        }
        else{
            if(!validateParliamentOption())
                throw new IllegalArgumentException("Wrong arguments: wrong global option");
        }
    }

    private boolean validateParliamentOption() {
        Option o = optionHashMap.get(argsToValidate[1]);

        if (o != null && o != Option.MEMBER_D_COSTS && o!= Option.MEMBER_S_COSTS){
            option = o;
            return true;
        }
        return false;
    }

    private boolean validateMemberName(){
        if (argsToValidate[3].equals("-d") || argsToValidate[3].equals("-s")){

            memberFirstName = argsToValidate[1];
            memberLastName = argsToValidate[2];
            option = optionHashMap.get(argsToValidate[3]);

            return true;
        }
        return false;
    }
    private void generateOptionHashMap() {

        optionHashMap.put("-d", Option.MEMBER_D_COSTS);
        optionHashMap.put("-s", Option.MEMBER_S_COSTS);
        optionHashMap.put("--average", Option.AVERAGE_ALL_COST);
        optionHashMap.put("--italy", Option.ITALY_JOURNEYS);
        optionHashMap.put("--mej", Option.MOST_EXPENSIVE_JOURNEY);
        optionHashMap.put("--mj", Option.MOST_JOURNEYS);
        optionHashMap.put("--lj", Option.LONGEST_JOURNEY);
    }
    private boolean validateCadency(){

        try{
            cadency = Integer.parseInt(argsToValidate[0]);
            if (cadency == 7 || cadency == 8)
                return true;
        }
        catch(NumberFormatException e){
            if (validateRomanCadency())
                return true;
        }
        return false;
    }

     private boolean validateRomanCadency() {

        switch(argsToValidate[0]){
            case "VII": this.cadency = 7; return true;
            case "VIII": this.cadency = 8; return true;
            default: return false;
        }
    }

    public String getMemberName() {
        return memberFirstName + " " + memberLastName;
    }

    public Option getOption() {
        return option;
    }
    public int getCadency(){
        return cadency;
    }
    public String linkGenerator(){
        String url =
                "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions%5Bposlowie.kadencja%5D=" + cadency +
                        "&_type=objects&page=";
        return url;
    }
}
