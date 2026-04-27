package com.pluralsight;

import java.util.Scanner;

public class AccountingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Home Screen");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make a payment");
            System.out.println("L) Ledger ");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice){

                case "D":
                    addTransaction(scanner,true);
                    break;
                case "P":
                    addTransaction(scanner,false);
                    break;
                case "L":
                    showLedgerScreen(scanner);
                    break;
                case "X":
                    return;
                default:
                    System.err.println("Invalid Choice");

            }

        }
    }

    private static void showLedgerScreen(Scanner scanner) {
    }

    private static void addTransaction(Scanner scanner, boolean b) {
    }


}
