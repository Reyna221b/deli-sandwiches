package org.pluralsight.models;

import org.pluralsight.services.PriceCalculator;

import java.util.ArrayList;
import java.util.List;

public class Sandwich
{
    private String bread;
    private int size;
    private List<Toppings> toppings;
    private boolean toasted;
    private List<Sauce> sauces;
    private List<Side> sides;


    public Sandwich(String bread, int size, boolean toasted)
    {

        this.bread = bread;
        this.size = size;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
    }

    public String getBread()
    {
        return bread;
    }

    public void setBread(String bread)
    {
        this.bread = bread;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public boolean isToasted()
    {
        return toasted;
    }

    public void addSauce(Sauce sauce)
    {
        sauces.add(sauce);
    }

    public void addSide(Side side)
    {
        sides.add(side);
    }
    public void setToasted(boolean toasted)
    {
        this.toasted = toasted;
    }

    public void addTopping(Toppings topping) {
        toppings.add(topping);
    }

    public void removeTopping(Toppings topping) {
        toppings.remove(topping);
    }

    public List<Toppings> getToppings() {
        return toppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }
    public List<Side> getSides(){
        return sides;
    }

    public String getSandwichDescription(Order order) {
        StringBuilder description = new StringBuilder();

        description.append("Sandwich: ").append(size).append("in. Price: $" + PriceCalculator.getSandwichSizeCost(size) +
                "\n\tBread Type: ").append(getBread()).append("\n");

        description.append("\t").append(isToasted() ? "Toasted" : "Not Toasted").append("\n");

        description.append("\tToppings:\n");
        int meatCount = 0;
        int cheeseCount = 0;
        boolean extraCheesePrinted = false;
        boolean extraMeatPrinted = false;

        boolean firstMeat = true;
        boolean firstCheese = true;
        for (Toppings topping : getToppings()) {
            if (topping instanceof Meat) {
                if (!((Meat) topping).getType().equals(Meat.NO_MEAT)){
                    meatCount++;
                    if (meatCount > 1 && !extraMeatPrinted) {
                        description.append("\t- Extra Meat: $ "+PriceCalculator.getAdditionalMeatCost(size)+"\n");
                        extraMeatPrinted = true;
                    }
                    if (firstMeat) {
                        description.append("\tMeat: $ " + PriceCalculator.getMeatBaseCost(size)+"\n");
                        firstMeat = false;
                    }
                    description.append("\t- ").append(((Meat) topping).getType()).append("\n");
                }
            } else if (topping instanceof Cheese) {
                if (!((Cheese) topping).getType().equals(Cheese.NO_CHEESE)) {
                    cheeseCount++;
                    if (cheeseCount > 1 && !extraCheesePrinted) {
                        description.append("\t- Extra Cheese:"+ PriceCalculator.getAdditionalCheeseCost(size)+"\n");
                        extraCheesePrinted = true;
                    }
                    if (firstCheese) {
                        description.append("\tCheese: $ " + PriceCalculator.getCheeseBaseCost(size)+"\n");
                        firstCheese = false;
                    }
                    description.append("\t- ").append(((Cheese) topping).getType()).append("\n");
                }
            }
        }
        description.append("\tRegular: free\n");
        for (Toppings topping : getToppings()) {
            if (!(topping instanceof Meat) && !(topping instanceof Cheese)) {
                description.append("\t- ").append(topping.getName()).append("\n");
            }
        }

            description.append("\tSauce:\n");
            for(Sauce sauce : getSauces()){
                description.append("\t- ").append(sauce.getName()).append("\n");
            }

            description.append("\tSides:\n");
            for(Side side : getSides()){
                description.append("\t- ").append(side.getName()).append("\n");
            }


        return description.toString();
    }

    public double calculateTotalCost() {

        return PriceCalculator.calculateToppingPrice(size,toppings);
    }


}


