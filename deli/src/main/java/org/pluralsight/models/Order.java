package org.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order
{

    private List<Sandwich> sandwiches;
    private List<Drinks> drinks;
    private List<Chips> chips;
    private List<Side> sides;


    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
        sides = new ArrayList<>();

    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drinks drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chips) {
        this.chips.add(chips);
    }
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    public void addSide(Side side) {
        sides.add(side);
    }
    public List<Side> getSides() {
        return sides;
    }

    public double getTotalPrice()
    {
        double totalCost = 0.0;
        for (Sandwich sandwich : sandwiches) {
            totalCost += sandwich.calculateTotalCost();
        }
        for (Drinks drink : drinks) {
            totalCost += drink.getPrice();
        }
        for (Chips chip : chips) {
            totalCost += chip.getPrice();
        }

        return totalCost;
    }


    public void clearOrder() {
        sandwiches.clear();
        drinks.clear();
        chips.clear();
    }

}
