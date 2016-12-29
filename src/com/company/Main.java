package com.company;

public class Main {

    public static void main(String[] args) {
    ArgumentParser argParser = new ArgumentParser(args);

    try{
        argParser.validateArgs();
    }
    catch (IllegalArgumentException e){
        System.out.println(e);
    }

	// write your code here
    }
}
