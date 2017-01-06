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

        Downloader d = new Downloader(argParser.linkGenerator() + 1);
        d.download();
        Parser parser = new Parser(argParser.getOption(), d.getJsonResult());
        parser.parseMainJson();

        Parliament parliament = new Parliament(argParser.getCadency());
        parliament.pushMembers(parser.getMembers());
        parliament.show();
       // parliament.generateAverageCosts();

        d = new Downloader(argParser.linkGenerator() + 1);
        d.download();


	// write your code here
    }
}
