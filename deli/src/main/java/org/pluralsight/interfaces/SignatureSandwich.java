package org.pluralsight.interfaces;

import org.pluralsight.models.Toppings;

import java.util.List;

public interface SignatureSandwich
{
    List<Toppings> getToppings();
    void addTopping(Toppings topping);
    void removeTopping(Toppings topping);
    String getName();

}
