package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class AccountingApp {

    public static void main(String[] args) {

        createFileHeader();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Home Screen");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make a payment");
            System.out.println("L) Ledger ");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {

                case "D":
                    addTransaction(scanner, true);
                    break;
                case "P":
                    addTransaction(scanner, false);
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

    private static void addTransaction(Scanner scanner, boolean isDeposit) {

        String description = requiredInput(scanner, "Description: ");
        String vendor = requiredInput(scanner, "Vendor: ");

        double amount;

        while (true) {
            System.out.println("Amount: ");
            String amountInput = scanner.nextLine().trim();

            try {
                amount = Double.parseDouble(amountInput);

                if (amount * 100 != Math.round(amount * 100)) {
                    System.err.println("Only 2 decimal places allowed.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number, like 25.50");
            }

        }


        if (isDeposit) {
            amount = Math.abs(amount);
        } else {
            amount = -Math.abs(amount);
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

            writer.write(transaction.csvString());
            writer.newLine();

            writer.close();

            System.out.println("Transaction saved!");


        } catch (IOException e) {

            System.err.println("There was a problem saving the transaction.");
        }

    }

    private static String requiredInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.err.println(prompt + "cannot be blank.");
        }
    }

    private static void createFileHeader(){
        try{
            File file = new File("src/main/resources/transactions.csv");

            if(!file.exists() || file.length() == 0){

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                writer.write("date|time|description|vendor|amount");
                writer.newLine();
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }




