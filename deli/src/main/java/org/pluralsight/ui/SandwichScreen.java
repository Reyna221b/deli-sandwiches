package org.pluralsight.ui;
import org.pluralsight.models.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SandwichScreen
{
    static final Scanner userInput = new Scanner(System.in);
    private static String breadType;
    private static int size;


    public static Sandwich buildSandwich()
    {

        System.out.println("\n=== Add Sandwich ===");
        size = selectSandwichSize();
        breadType = selectBreadType();
        boolean toasted = isToasted();

        Sandwich sandwich = new Sandwich(breadType, size, toasted);

        getMeatChoice(sandwich);

        getCheeseChoice(sandwich);

        toppingChoice(sandwich);

        getSauceChoice(sandwich);

        getSideChoice(sandwich);

        System.out.println("Sandwich added to Order!!");

        return sandwich;

    }

    public static void getSideChoice(Sandwich sandwich) {

        System.out.println("\nSelect your side:");
        System.out.println("=".repeat(16));
        System.out.println("1) Au Jus");
        System.out.println("2) Homemade Sauce");
        System.out.println("0) no side");
        System.out.println("=".repeat(16));
        System.out.print("Enter choice: ");
        String sideChoice = userInput.nextLine();

        try {
            int sideInput = Integer.parseInt(sideChoice.strip());

            if (sideInput < 0 || sideInput > 2) {
                System.out.println("Invalid side choice.");
                return;
            }
            switch (sideInput) {

                case 0:
                    sandwich.addSide(new Side("No Side"));
                    break;
                case 1:
                    sandwich.addSide(new Side("Au Jus"));
                    break;
                case 2:
                    sandwich.addSide(new Side("Homemade Sauce"));
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid side choice.");
        }
    }

    private static void getSauceChoice(Sandwich sandwich)
    {
        System.out.println("\nSelect your sauce:");
        System.out.println("=".repeat(16));

        System.out.println("1) Mayo");
        System.out.println("2) Mustard");
        System.out.println("3) Ketchup");
        System.out.println("4) Ranch");
        System.out.println("5) Thousand Islands");
        System.out.println("6) Vinaigrette");
        System.out.println("0) No Sauce");
        System.out.println("=".repeat(16));
        System.out.print("Enter your choices (numbers separated by a comma or space): ");
        String saucesInput = userInput.nextLine();
        String[] sauces = saucesInput.split("[,\\s]+");

        for (String sauce : sauces) {
            try {
                int sauceChoice = Integer.parseInt(sauce.strip());
                if (sauceChoice < 0 || sauceChoice > 6) {
                    System.out.println("Invalid topping choice.");
                    continue;
                }

                switch (sauceChoice) {
                    case 0:
                        sandwich.addSauce(new Sauce("No Sauce"));
                        break;
                    case 1:
                        sandwich.addSauce(new Sauce("Mayo"));
                        break;
                    case 2:
                        sandwich.addSauce(new Sauce("Mustard"));
                        break;
                    case 3:
                        sandwich.addSauce(new Sauce("Ketchup"));
                        break;
                    case 4:
                        sandwich.addSauce(new Sauce("Ranch"));
                        break;
                    case 5:
                        sandwich.addSauce(new Sauce("Thousand Islands"));
                        break;
                    case 6:
                        sandwich.addSauce(new Sauce("Vinaigrette"));
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input.");

            }
        }
    }

    private static void getCheeseChoice(Sandwich sandwich)
    {
        System.out.println("\nSelect Cheese toppings:" );
        System.out.println("Cheeses:");
        System.out.println("=".repeat(16));
        System.out.println("1) Cheddar");
        System.out.println("2) Swiss");
        System.out.println("3) Mozzarella");
        System.out.println("4) American");
        System.out.println("5) Gouda");
        System.out.println("6) Provolone");
        System.out.println("0) No Cheese");
        System.out.println("=".repeat(16));
        System.out.print("Enter your choice:  ");
        int cheeseChoice = Integer.parseInt(userInput.nextLine().strip());

        switch (cheeseChoice) {
            case 0:
                sandwich.addTopping(new Cheese(Cheese.NO_CHEESE));
                break;
            case 1:
                sandwich.addTopping(new Cheese(Cheese.CHEDDAR));
                break;
            case 2:
                sandwich.addTopping(new Cheese(Cheese.SWISS));
                break;
            case 3:
                sandwich.addTopping(new Cheese(Cheese.MOZZARELLA));
                break;
            case 4:
                sandwich.addTopping(new Cheese(Cheese.AMERICAN));
                break;
            case 5:
                sandwich.addTopping(new Cheese(Cheese.GOUDA));
                break;
            case 6:
                sandwich.addTopping(new Cheese(Cheese.PROVOLONE));
                break;

            default:
                System.out.println("Invalid input.");
        }

        boolean wantsExtraCheese = false;
        if(cheeseChoice != 0){
            while (!wantsExtraCheese)
            {
                System.out.print("\nDo you want extra cheese? (Yes/No): ");
                String extraMeatChoice = userInput.nextLine().toLowerCase().strip();
                wantsExtraCheese = extraMeatChoice.equals("yes");

                if (wantsExtraCheese) {
                    getCheeseChoice(sandwich);
                }
                else{
                    return;
                }

            }

        }

    }

    private static void getMeatChoice(Sandwich sandwich)
    {
        System.out.println("\nSelect toppings:");
        System.out.println("Meats:");
        System.out.println("=".repeat(16));
        System.out.println("1) Steak");
        System.out.println("2) Ham");
        System.out.println("3) Salami");
        System.out.println("4) Roast Beef");
        System.out.println("5) Chicken");
        System.out.println("6) Bacon");
        System.out.println("7) Meatball");
        System.out.println("0) No Meat");
        System.out.println("=".repeat(16));;
        System.out.print("Enter your choice:  ");
        int meatChoice = Integer.parseInt(userInput.nextLine().strip());

        switch (meatChoice) {
            case 0:
                sandwich.addTopping(new Meat(Meat.NO_MEAT));
                break;
            case 1:
                sandwich.addTopping(new Meat(Meat.STEAK));
                break;
            case 2:
                sandwich.addTopping(new Meat(Meat.HAM));
                break;
            case 3:
                sandwich.addTopping(new Meat(Meat.SALAMI));
                break;
            case 4:
                sandwich.addTopping(new Meat(Meat.ROAST_BEEF));
                break;
            case 5:
                sandwich.addTopping(new Meat(Meat.CHICKEN));
                break;
            case 6:
                sandwich.addTopping(new Meat(Meat.BACON));
                break;
            case 7:
                sandwich.addTopping(new Meat(Meat.MEATBALL));
                break;
            default:
                System.out.println("Invalid input.");
        }

        boolean wantsExtraMeat;
        if(meatChoice != 0){
            while (true)
            {
                System.out.print("\nDo you want extra meat? (Yes/No): ");
                String extraMeatChoice = userInput.nextLine().toLowerCase().strip();
                wantsExtraMeat = extraMeatChoice.equals("yes");

                if (wantsExtraMeat) {
                    getMeatChoice(sandwich);
                }
                else{
                    return;
                }
                break;
            }
        }


    }

    public static void toppingChoice(Sandwich sandwich) {

        System.out.println("\nSelect your toppings:");
        System.out.println("=".repeat(16));
        System.out.println("1) Lettuce");
        System.out.println("2) Peppers");
        System.out.println("3) Onions");
        System.out.println("4) Tomatoes");
        System.out.println("5) Jalapenos");
        System.out.println("6) Cucumbers");
        System.out.println("7) Pickles");
        System.out.println("8) Guacamole");
        System.out.println("9) Mushrooms");
        System.out.println("0) No Topping");
        System.out.println("=".repeat(16));

        System.out.print("Enter your choices (numbers separated by a comma or space): ");
        String toppingInput = userInput.nextLine();

        String[] toppings = toppingInput.split("[,\\s]+");

        for (String topping : toppings) {
            try {
                int toppingChoice = Integer.parseInt(topping.strip());

                if (toppingChoice < 0 || toppingChoice > 9) {
                    System.out.println("Invalid topping choice.");
                    continue;
                }

                switch (toppingChoice) {
                    case 0:
                        sandwich.addTopping(new Toppings("No Toppings"));
                        break;
                    case 1:
                        sandwich.addTopping(new Toppings("Lettuce"));
                        break;
                    case 2:
                        sandwich.addTopping(new Toppings("Peppers"));
                        break;
                    case 3:
                        sandwich.addTopping(new Toppings("Onions"));
                        break;
                    case 4:
                        sandwich.addTopping(new Toppings("Tomatoes"));
                        break;
                    case 5:
                        sandwich.addTopping(new Toppings("Jalapenos"));
                        break;
                    case 6:
                        sandwich.addTopping(new Toppings("Cucumbers"));
                        break;
                    case 7:
                        sandwich.addTopping(new Toppings("Pickles"));
                        break;
                    case 8:
                        sandwich.addTopping(new Toppings("Guacamole"));
                        break;
                    case 9:
                        sandwich.addTopping(new Toppings("Mushrooms"));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    private static boolean isToasted()
    {
        System.out.print("\nWould you like the sandwich toasted? (yes/no): ");
        boolean toasted;
        while (true) {
            String toastedChoice = userInput.nextLine();
            if (toastedChoice.equalsIgnoreCase("yes") || toastedChoice.equalsIgnoreCase("y")) {
                toasted = true;
                break;
            } else if (toastedChoice.equalsIgnoreCase("no") || toastedChoice.equalsIgnoreCase("n")) {
                toasted = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        return toasted;
    }

    public static String selectBreadType()
    {

        while (true) {
            System.out.println("Select your bread type:");
            System.out.println("=".repeat(16));
            System.out.println("1. White");
            System.out.println("2. Wheat");
            System.out.println("3. Italian");
            System.out.println("4. Wrap");
            System.out.println("=".repeat(16));
            System.out.print("Enter Choice: ");

            try {
                int breadChoice = Integer.parseInt(userInput.nextLine().strip());
                switch (breadChoice) {

                        case 1:
                            return "White";
                        case 2:
                            return "Wheat";
                        case 3:
                            return "Italian";
                        case 4:
                            return "Wrap";
                        default:
                            System.out.println("Invalid choice.");
                }
            }
            catch(InputMismatchException e){
                    System.out.println("Invalid input.");
            }
        }

    }


    private static int selectSandwichSize() {
        while (true) {
            System.out.print("\nEnter sandwich size (4, 8, or 12 inches): ");
            try {
                size = Integer.parseInt(userInput.nextLine().strip());

                if (size == 4 || size == 8 || size == 12) {
                    return size;
                } else {
                    System.out.println("Invalid size. Please enter 4, 8, or 12.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }
    }

}
