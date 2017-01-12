package com.company;

import java.io.IOException;

public class Main {

    public static boolean launch(String[] args) {
        ArgumentParser argParser = new ArgumentParser(args);

        try {
            argParser.validateArgs();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }

        Parliament parliament = new Parliament(argParser.getCadency());
        Parser parser;
        int page = 1;

        do {
            System.out.println("Przetwarzanie strony: " + page);
            Downloader d = new Downloader(argParser.linkGenerator() + page);
            try {
                d.download();
            } catch (IOException e) {
                System.out.println(e);
                return false;
            }
            if (argParser.getOption().equals(Option.MEMBER_D_COSTS) || argParser.getOption().equals(Option.MEMBER_S_COSTS)) {
                parser = new Parser(argParser.getOption(), d.getJsonResult(), argParser.getMemberName());
            } else {
                parser = new Parser(argParser.getOption(), d.getJsonResult());
            }

            parser.parseMainJson();
            parliament.pushMembers(parser.getMembers());
            page++;
        }
        while (parser.ifNextPage());
        // parliament.show();
        try {
            switch (argParser.getOption()) {
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
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
