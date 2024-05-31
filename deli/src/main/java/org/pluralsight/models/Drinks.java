package org.pluralsight.models;

public class Drinks extends Order
{

    private String size;
    private String flavor;
    private double price;

    public Drinks(String size, String flavor, double price)
    {
        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }

    public String getFlavor()
    {
        return flavor;
    }

    public void setFlavor(String flavor)
    {
        this.flavor = flavor;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

}
