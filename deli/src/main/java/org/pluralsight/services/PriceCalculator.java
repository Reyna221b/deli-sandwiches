package org.pluralsight.services;

import org.pluralsight.models.*;

import java.util.List;

public class PriceCalculator {

    private static double meatBasePrice;
    private static double cheeseBasePrice;


    public static double calculateToppingPrice(int size, List<Toppings> toppings) {

        double total = getSandwichSizeCost(size);
        double additionalMeatCost = getAdditionalMeatCost(size);
        double additionalCheeseCost = getAdditionalCheeseCost(size);
        boolean isFirstMeat, isFirstCheese;

        isFirstMeat = true;
        isFirstCheese = true;

        for (Toppings topping : toppings) {
            if (topping instanceof Meat) {
                if (((Meat) topping).getType().equals(Meat.NO_MEAT)){
                    continue;
                }
                if (isFirstMeat) {
                    total += getMeatBaseCost(size) ;
                    isFirstMeat = false;

                } else {
                    total += additionalMeatCost;
                }
            } else if (topping instanceof Cheese) {
                if (((Cheese) topping).getType().equals(Cheese.NO_CHEESE)) {
                    continue;
                }
                if (isFirstCheese) {

                    total += getCheeseBaseCost(size) ;
                    isFirstCheese = false;

                } else {
                    total += additionalCheeseCost;
                }
            }
        }

        return total;
    }


    public static double getDrinkTotal(Order order){
        double drink = 0;
        for(Drinks d: order.getDrinks())
        {
            drink += d.getPrice();
        }
        return drink;
    }

    public static double getChipsTotal(Order order){
        double chips = 0;
        for(Chips c: order.getChips())
        {
            chips += c.getPrice();
        }
        return chips;
    }

    public static double getCheeseBaseCost(int size)
    {
        switch (size) {
            case 4 -> cheeseBasePrice = .75;
            case 8 -> cheeseBasePrice = 1.50;
            case 12 -> cheeseBasePrice = 2.25;
        }
        return cheeseBasePrice;
    }

    public static double getSandwichSizeCost(int size) {
        return switch (size) {
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 0;
        };
    }

    public static double getAdditionalMeatCost(int size) {
        return switch (size) {
            case 4 -> 0.50;
            case 8 -> 1.00;
            case 12 -> 1.50;
            default -> 0;
        };
    }

    public static double getAdditionalCheeseCost(int size) {
        return switch (size) {
            case 4 -> 0.30;
            case 8 -> 0.60;
            case 12 -> 0.90;
            default -> 0;
        };
    }

    public static double getMeatBaseCost(int size)
    {
        switch (size) {
            case 4:
                meatBasePrice = 1.00;
                break;
            case 8:
                meatBasePrice = 2.00;
                break;
            case 12:
                meatBasePrice = 3.00;
                break;
        }
        return meatBasePrice;
    }

    public static double getDrinkSizePrice(String drinkSize) {
        double drinkPrice = 0;

        if (drinkSize.equalsIgnoreCase("small") || drinkSize.equalsIgnoreCase("s")
                || drinkSize.equals("1")) {
            drinkPrice = 2.00;
        } else if (drinkSize.equalsIgnoreCase("medium") || drinkSize.equalsIgnoreCase("m")
                || drinkSize.equals("2")) {
            drinkPrice = 2.50;
        } else if (drinkSize.equalsIgnoreCase("large") || drinkSize.equalsIgnoreCase("l")
                || drinkSize.equals("3")) {
            drinkPrice = 3.00;
        } else {
            System.out.println("Invalid drink size. Please select a correct size");
            drinkPrice = 0;
        }
        return drinkPrice;
    }
}