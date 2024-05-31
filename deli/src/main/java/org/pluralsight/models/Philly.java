package org.pluralsight.models;

import org.pluralsight.interfaces.SignatureSandwich;

import java.util.List;

public class Philly extends Sandwich implements SignatureSandwich
{
    public Philly(int size) {
        super("White", size,true);
        addSauce(new Sauce("Mayo"));
        addMeat(new Meat(Meat.STEAK));
        addCheese(new Cheese(Cheese.CHEDDAR));
        addTopping(new Toppings("Pepper"));
    }

    public void addMeat(Meat meat) {
        addTopping(meat);
    }

    public void addCheese(Cheese cheese) {
        addTopping(cheese);
    }

    @Override
    public List<Toppings> getToppings() {
        return super.getToppings();
    }

    @Override
    public void addTopping(Toppings topping) {
        super.addTopping(topping);
    }

    @Override
    public void removeTopping(Toppings topping) {
        super.removeTopping(topping);
    }

    @Override
    public String getName() {
        return "Philly Cheese Steak";
    }
}
