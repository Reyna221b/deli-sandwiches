package org.pluralsight.models;

public class Meat extends Toppings
{

    public static final String STEAK = "Steak";
    public static final String HAM = "Ham";
    public static final String SALAMI = "Salami";
    public static final String ROAST_BEEF = "Roast Beef";
    public static final String CHICKEN = "Chicken";
    public static final String BACON = "Bacon";
    public static final String MEATBALL = "Meatball";
    public static final String NO_MEAT = "No Meat";



    private String type;

    public Meat(String type) {
        super("Meat");
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
