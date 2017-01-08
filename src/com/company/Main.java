package com.company;

public class Main {

    public static void main(String[] args) {
    ArgumentParser argParser = new ArgumentParser(args);

    try{
        argParser.validateArgs();
    }
    catch (IllegalArgumentException e){
        System.out.println(e);
        System.exit(1);
    }

    Parliament parliament = new Parliament(argParser.getCadency());

    for (int i = 1; i < 16; i++){
        Downloader d = new Downloader(argParser.linkGenerator() + i);
        d.download();
        Parser parser;
        if(argParser.getOption().equals(Option.MEMBER_D_COSTS) || argParser.getOption().equals(Option.MEMBER_S_COSTS)){
            parser = new Parser(argParser.getOption(), d.getJsonResult(), argParser.getMemberName());
        }
        else{
            parser = new Parser(argParser.getOption(), d.getJsonResult());
        }

        parser.parseMainJson();

        parliament.pushMembers(parser.getMembers());
    }

    parliament.show();

    switch(argParser.getOption()){
        case MEMBER_S_COSTS:
            System.out.println(parliament.generateAverageCosts());
            break;
        case MEMBER_D_COSTS:
            System.out.println(parliament.getMemberLittleCosts());
            break;
        case AVERAGE_ALL_COST:
            System.out.println(parliament.generateAverageCosts());
            break;
        case ITALY_JOURNEYS:
            System.out.println(parliament.getMembersItalyVoyages());
            break;
        case MOST_JOURNEYS:
            System.out.println(parliament.getMemberWithMostJourneys());
            break;
        case LONGEST_JOURNEY:
            System.out.println(parliament.getMemberWithLongestJourneys());
            break;
        case MOST_EXPENSIVE_JOURNEY:
            System.out.println(parliament.getMemberWithMostExpensiveJourney());
        default: break;
    }




	// write your code here
    }
}
