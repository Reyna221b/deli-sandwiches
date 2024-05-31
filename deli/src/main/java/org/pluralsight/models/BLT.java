package org.pluralsight.models;

import org.pluralsight.interfaces.SignatureSandwich;

import java.util.List;

public class BLT extends Sandwich implements SignatureSandwich {
    public BLT(int size) {
        super("White", size,true);

        addTopping(new Toppings("Lettuce"));
        addTopping(new Toppings("Tomato"));
        addSauce(new Sauce("Ranch"));
        addMeat(new Meat(Meat.BACON));
        addCheese(new Cheese(Cheese.CHEDDAR));
        setToasted(true);
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
        return "BLT";
    }
}
