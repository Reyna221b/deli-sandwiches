package org.pluralsight.ui;

import org.pluralsight.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SignatureSandwichScreen
{
    static final Scanner userInput = new Scanner(System.in);
    static Sandwich sandwich;

    //USE ENUMS FOR OPTIONS AND SWITCH FUTURE ME

    public static Sandwich addSignatureSandwich() {

        System.out.println("\nSelect a signature sandwich:");
        System.out.println("=".repeat(30));
        System.out.println("1) BLT");
        System.out.println("2) Philly");
        System.out.println("3) Create your own");
        System.out.println("4) Back to Home screen");
        System.out.println("=".repeat(30));
        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(userInput.nextLine().strip());

        switch (choice) {
            case 1:
                System.out.print("\nSandwich Size (4in, 8in 0r 12in): ");
                int size = Integer.parseInt(userInput.nextLine().strip());
                 sandwich = new BLT(size);
                 System.out.print("\nWould you like to customize your " + "BLT?: ");
                 String customizeChoice = userInput.nextLine().strip().toLowerCase();
                if (customizeChoice.equalsIgnoreCase("yes")){
                    customizeSandwich(sandwich);
                }
                else {
                    System.out.println("Signature Sandwich Added!");
                }

                break;
            case 2:
                System.out.print("\nSandwich Size (4in, 8in 0r 12in): ");
                int size1 = Integer.parseInt(userInput.nextLine().strip());
                sandwich =  new Philly(size1);
                System.out.print("\nWould you like to customize your " + "Philly Cheese Steak?:  ");
                String customizeInput = userInput.nextLine().strip().toLowerCase();
                if (customizeInput.equalsIgnoreCase("yes")){
                    customizeSandwich(sandwich);
                }
                else{
                    System.out.println("Signature Sandwich Added!");
                }

                break;
            case 3:
                SandwichScreen.buildSandwich();
            case 4:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
        return sandwich;

    }

    private static void customizeSandwich(Sandwich sandwich1)
    {
        while (true) {
            System.out.println("\nWhat would you like to customize:");
            System.out.println("=".repeat(30));
            System.out.println("1) Bread");
            System.out.println("2) Add Toppings");
            System.out.println("3) Remove Toppings");
            System.out.println("4) Toasted");
            System.out.println("0) Done");
            System.out.println("=".repeat(30));
            System.out.print("Enter Choice: ");

            int optionChoice = Integer.parseInt(userInput.nextLine().strip());
            switch (optionChoice) {

                case 1:
                    System.out.println();
                    System.out.println("Select your bread type:");
                    System.out.println("=".repeat(16));
                    System.out.println("1. White");
                    System.out.println("2. Wheat");
                    System.out.println("3. Italian");
                    System.out.println("4. Wrap");
                    System.out.println("=".repeat(16));
                    System.out.print("Enter Choice: ");

                    int breadType = Integer.parseInt(userInput.nextLine().strip());
                    switch (breadType) {
                        case 1:
                            sandwich.setBread("White");
                            break;
                        case 2:
                            sandwich.setBread("Wheat");
                            break;
                        case 3:
                            sandwich.setBread("Italian");
                            break;
                        case 4:
                            sandwich.setBread("Wrap");
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + breadType);
                    }
                    break;
                case 2:
                    List<Toppings> availableToppings = getAvailableToppings();
                    List<Toppings> sandwichToppings = sandwich.getToppings();
                    while (true) {
                        System.out.println("\nSelect a topping to add:");
                        System.out.println("=".repeat(30));
                        List<Toppings> toppingsToDisplay = new ArrayList<>();
                        for (Toppings topping : availableToppings) {
                            if (!sandwichToppings.contains(topping)) {
                                toppingsToDisplay.add(topping);
                            }
                        }
                        for (int i = 0; i < toppingsToDisplay.size(); i++) {
                            System.out.println((i + 1) + ") " + toppingsToDisplay.get(i).getName());
                        }
                        System.out.println((toppingsToDisplay.size() + 1) + ") Done");
                        System.out.println("=".repeat(30));
                        System.out.print("Enter Choice: ");
                        int addToppingChoice = Integer.parseInt(userInput.nextLine().strip());
                        if (addToppingChoice == toppingsToDisplay.size() + 1) {
                            break;
                        } else if (addToppingChoice > 0 && addToppingChoice <= toppingsToDisplay.size()) {
                            sandwich.addTopping(toppingsToDisplay.get(addToppingChoice - 1));
                            System.out.println("Topping added!");
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 3:
                    List<Toppings> toppings = sandwich.getToppings();
                    while (true) {
                        System.out.println("\nSelect a topping to remove:");
                        System.out.println("=".repeat(30));
                        for (int i = 0; i < toppings.size(); i++) {
                            System.out.println((i + 1) + ") " + toppings.get(i).getName());
                        }
                        System.out.println((toppings.size() + 1) + ") Done");
                        System.out.println("=".repeat(30));
                        System.out.print("Enter Choice: ");
                        int removeToppingChoice = Integer.parseInt(userInput.nextLine().strip());
                        if (removeToppingChoice == toppings.size() + 1) {
                            break;
                        } else if (removeToppingChoice > 0 && removeToppingChoice <= toppings.size()) {
                            sandwich.removeTopping(toppings.get(removeToppingChoice - 1));
                            System.out.println("Topping removed!");
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;

                case 4:
                    if (sandwich.isToasted()) {
                        System.out.print("\nWould you like your sandwich toasted? (Yes/No): ");
                        String response = userInput.nextLine().strip().toLowerCase();
                        if (response.equals("yes")) {
                            sandwich.setToasted(true);
                        }
                    } else {
                        System.out.print("\nWould you like your sandwich Not toasted? (Yes/No): ");
                        String response = userInput.nextLine().strip().toLowerCase();
                        if (response.equals("yes")) {
                            sandwich.setToasted(false);
                        }
                    }
                    break;
                case 0:
                    break;
            }
            if (optionChoice == 0) {
                break;
            }
        }
        System.out.println("\n Signature Sandwich added! \n");


    }


    private static List<Toppings> getAvailableToppings()
    {
        List<Toppings> availableToppings = new ArrayList<>();
        availableToppings.add(new Toppings("Lettuce"));
        availableToppings.add(new Toppings("Peppers"));
        availableToppings.add(new Toppings("Onions"));
        availableToppings.add(new Toppings("Tomatoes"));
        availableToppings.add(new Toppings("Jalapenos"));
        availableToppings.add(new Toppings("Cucumbers"));
        availableToppings.add(new Toppings("Pickles"));
        availableToppings.add(new Toppings("Guacamole"));
        availableToppings.add(new Toppings("Mushrooms"));
        return availableToppings;
    }
}
