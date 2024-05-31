package org.pluralsight.ui;

import org.pluralsight.interfaces.SignatureSandwich;
import org.pluralsight.models.*;
import org.pluralsight.services.PriceCalculator;
import org.pluralsight.services.ReceiptFileManager;

import java.util.Scanner;

public class MenuScreen
{
    static final Scanner userInput = new Scanner(System.in);
    private static Order currentOrder;
    static SandwichScreen sandwichScreen = new SandwichScreen();
    static SignatureSandwichScreen signatureSandwich;
    static PriceCalculator priceCalculator;
    static Sandwich sandwich;
    static Drinks drinks;
    static Chips chips;
    static double drinkTotal;
    static double chipsTotal;
    static double sandwichTotal;
    static ReceiptFileManager receiptFileManager = new ReceiptFileManager();


    public void menuDisplay()
    {

        System.out.println("\t\t\t_______❀️********❀️_______");
        System.out.println("\t\t\t|                        |");
        System.out.println("\t\t\t|        Welcome 🍞️      |");
        System.out.println("\t\t\t|           To           |");
        System.out.println("\t\t\t|     ✨DELI-cious✨     |");
        System.out.println("\t\t\t|                        |");
        System.out.println("\t\t\t_______❀️********❀️_______");

        while (true) {
            try {
                System.out.println();
                System.out.println("                🍔 Home Screen 🍟");
                System.out.println("________________________________________________________");
                System.out.println(" [1] - New Order");
                System.out.println(Colors.RED + " [0] - Quit" + Colors.RESET);
                System.out.println("----------------------------------------------------------");
                System.out.println();
                System.out.print(Colors.CYAN + "Enter your choice : " + Colors.RESET);
                int choice = Integer.parseInt(userInput.nextLine().strip());

                switch (choice) {
                    case 1:
                        startNewOrder();
                        displayOrderScreen();
                        break;
                    case 0:
                        System.out.println();
                        System.out.println("Goodbye for now " + "❗️ \uD83D\uDC4B ");
                        return;
                    default:
                        System.out.println("Invalid Input.");
                }
            } catch (Exception ex) {
                System.out.println(Colors.RED + "invalid selection!" + Colors.RESET);
            }

        }

    }

    private static void startNewOrder()
    {
        currentOrder = new Order();
        System.out.println();
        System.out.println("🌟🎉 Starting new order!🥪🌟");
        System.out.println();
    }

    public static void displayOrderScreen()
    {
        while (true) {

            try {
                System.out.println("\n=== Order Screen ===");
                System.out.println("1) Add Sandwich");
                System.out.println("2) Add Signature Sandwich");
                System.out.println("3) Add Drink");
                System.out.println("4) Add Chips");
                System.out.println("5) Checkout");
                System.out.println("0) Cancel Order");
                System.out.println("=".repeat(16));
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(userInput.nextLine().strip());

                switch (choice) {
                    case 0:
                        System.out.println("Order cancelled.");
                        return;
                    case 1:
                        sandwich = SandwichScreen.buildSandwich();
                        currentOrder.addSandwich(sandwich);
                        break;
                    case 2:
                        sandwich = SignatureSandwichScreen.addSignatureSandwich();
                        currentOrder.addSandwich(sandwich);
                        break;
                    case 3:
                        drinks = DrinkScreen.addDrink();
                        currentOrder.addDrink(drinks);
                        break;
                    case 4:
                         chips = ChipsScreen.addChips();
                        currentOrder.addChips(chips);
                        break;
                    case 5:
                        checkout();
                        return;
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }

            } catch (Exception ex) {
                System.out.println(Colors.RED + "invalid selection!" + Colors.RESET);
            }

        }
    }

    private static void checkout()
    {
        sandwichTotal =0;
        double orderTotal = 0;
        System.out.println();
        System.out.println(Colors.PURPLE + "-".repeat(20)+"---- ORDER DETAILS ----" + "-".repeat(20)+ Colors.RESET);
        System.out.println(Colors.BLUE);
        if(!currentOrder.getSandwiches().isEmpty())
        {
            for(Sandwich s: currentOrder.getSandwiches()) {
                sandwichTotal += s.calculateTotalCost();
            }
        }
        if(!currentOrder.getDrinks().isEmpty())
        {
            drinkTotal = PriceCalculator.getDrinkTotal(currentOrder);
        }
        if(!currentOrder.getChips().isEmpty())
        {
            chipsTotal = PriceCalculator.getChipsTotal(currentOrder);
        }
        orderTotal = sandwichTotal + drinkTotal + chipsTotal;

        orderDescription(currentOrder);
        System.out.println("-".repeat(65));
        System.out.println("ORDER TOTAL: $ " + orderTotal );
        System.out.println("-".repeat(65)+ Colors.RESET);

        System.out.println("1) Confirm");
        System.out.println("2) Cancel");
        System.out.println("3) Add More");
        System.out.println("-".repeat(65));

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(userInput.nextLine().strip());

        if (choice == 1) {

            receiptFileManager.writeOrderToFile(currentOrder);
            System.out.println("\nOrder confirmed.");
            currentOrder.clearOrder();

        } else if (choice == 2) {
            currentOrder.clearOrder();
            System.out.println("\nOrder canceled.");
        } else if(choice == 3){
            displayOrderScreen();
        }
        else {
            System.out.println("\nInvalid choice.");
        }

    }

    private static void orderDescription(Order order)
    {
        if (!order.getSandwiches().isEmpty()) {
            getSandwichOrderDescription(order);
        }
        if (!order.getDrinks().isEmpty()) {
            getDrinkDescription(order);
        }
        if (!order.getChips().isEmpty()) {
            getChipDescription(order);
        }

    }

    private static void getChipDescription(Order order)
    {
        System.out.println("\n*******Chips********* ");
        for(Chips c: order.getChips()){

            System.out.println("\t- " + c.getType());
        }
        if(!order.getChips().isEmpty()){
            System.out.println("Chips total:$ " + chipsTotal);
        }
    }

    private static void getDrinkDescription(Order order)
    {
        System.out.println("\n*******Drinks********* ");
        for(Drinks drinks1: order.getDrinks())
        {
            System.out.println("\t- " + drinks1.getSize() + " " + drinks1.getFlavor());;
        }
        if(!order.getDrinks().isEmpty()){

            System.out.println("Drinks total:$ " + drinkTotal);
        }
    }

    private static void getSandwichOrderDescription(Order order)
    {
        double total = 0;
        System.out.println("\n*******Sandwiches********* ");
        for(Sandwich s : order.getSandwiches()) {
            System.out.println(s.getSandwichDescription(order));
            total+= s.calculateTotalCost();
            System.out.println("Sandwich price: " + s.calculateTotalCost());
        }
        if(!order.getSandwiches().isEmpty()){
            System.out.println("\nSandwiches total:$ " + total);
        }
    }

}






