package org.pluralsight.models;

public class Chips
{

    private String type;
    private double price;

    public Chips(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }


}
