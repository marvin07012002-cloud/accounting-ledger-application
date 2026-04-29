package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("Marbin");
        nameList.add("Dave");
        nameList.add("Fred");


        for (String currentString : nameList) {
            String name = "Mr. " + currentString;
            System.out.println(name);
        }
    }
}
