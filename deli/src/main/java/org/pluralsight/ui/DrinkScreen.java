package org.pluralsight.ui;

import org.pluralsight.models.Drinks;
import org.pluralsight.services.PriceCalculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DrinkScreen
{
    static final Scanner userInput = new Scanner(System.in);
    private static double drinkPrice;

    public static Drinks addDrink() {


        System.out.println("\nSelect drink size:");
        System.out.println("=".repeat(16));
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        System.out.println("0) Go Back");
        System.out.println("=".repeat(16));


        String sizeChoice;
        while (true) {
            System.out.print("Enter your choice: ");
            sizeChoice = userInput.nextLine().toLowerCase().strip();
            if(sizeChoice.equalsIgnoreCase("1"))
            {
                sizeChoice = "Small";
            } else if (sizeChoice.equalsIgnoreCase("2")) {
                sizeChoice ="Medium";

            } else if (sizeChoice.equalsIgnoreCase("3")) {
                sizeChoice ="Large";
            }
            else if(sizeChoice.equalsIgnoreCase("0")){
                break;
            }
            drinkPrice = PriceCalculator.getDrinkSizePrice(sizeChoice);
            if(drinkPrice !=0){
                break;
            }
        }

        System.out.println("\nSelect drink type:");
        System.out.println("=".repeat(16));

        String[] drinkOptions = {"Fountain Drink", "Lemonade", "Iced Tea"};


        for (int i = 0; i < drinkOptions.length; i++) {
            System.out.println((i + 1) + ") " + drinkOptions[i]);
        }
        System.out.println("=".repeat(16));

        int drinkChoice;
        while (true) {
            try {
                System.out.print("Enter choice: ");
                drinkChoice = Integer.parseInt(userInput.nextLine().strip());
                if (drinkChoice >= 1 && drinkChoice <= drinkOptions.length) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + drinkOptions.length + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        String drinkType = drinkOptions[drinkChoice - 1];
        Drinks drink = new Drinks(sizeChoice, drinkType, drinkPrice);
        System.out.println();
        System.out.println(sizeChoice + " " + drinkType + " added to the order!!");
        return drink;
    }
}
