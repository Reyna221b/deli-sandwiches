package org.pluralsight.ui;

import org.pluralsight.models.Chips;

import java.util.Scanner;

public class ChipsScreen
{
    static final Scanner userInput = new Scanner(System.in);

    public static Chips addChips(){

        String[] chipTypes = {"Regular", "BBQ", "Spicy", "Salt & Vinegar"};
        double chipPrice = 1.50;

        int chipChoice;

        System.out.println("\nSelect chip type:");
        System.out.println("=".repeat(16));

        for (int i = 0; i < chipTypes.length; i++) {
            System.out.println((i + 1) + ") " + chipTypes[i]);
        }

        while (true) {
            System.out.println("=".repeat(16));
            System.out.print("Enter your choice: ");

            String chipInput = userInput.nextLine().strip();
            try {
                chipChoice = Integer.parseInt(chipInput);
                if (chipChoice >= 1 && chipChoice <= chipTypes.length) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + chipTypes.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        String chipType = chipTypes[chipChoice - 1];
        Chips chips = new Chips(chipType, chipPrice);

        System.out.println("\n" + chipType + " chips added to the order!!");

        return chips;
    }
}
