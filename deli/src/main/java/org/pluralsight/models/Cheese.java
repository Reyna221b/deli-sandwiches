package org.pluralsight.models;

public class Cheese extends Toppings
{
    public static final String CHEDDAR = "Cheddar";
    public static final String SWISS = "Swiss";
    public static final String MOZZARELLA = "Mozzarella";
    public static final String AMERICAN = "American";
    public static final String GOUDA = "Gouda";
    public static final String PROVOLONE = "Provolone";
    public static final String NO_CHEESE = "No Cheese";

    private String type;

    public Cheese(String type) {
        super("Cheese");
        this.type = type;
    }

    public String getType() {
        return type;
    }
}